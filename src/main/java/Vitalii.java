import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Vitalii {
    public static class Item {

        private final int id;
        private final int prio;
        private final Item refItem;

        public Item(int id, int prio) {
            this(id, prio, null);
        }

        public Item(int id, int prio, Item refItem) {
            this.id = id;
            this.prio = prio;
            this.refItem = refItem;
        }

        public int getPrio() {
            return prio;
        }

        public Item getRefItem() {
            return refItem;
        }

        @Override
        public String toString() {
            return String.format("i%d", id);
        }
    }

    public static void main(String[] args) {
        /*
         * Given:
         * List of linked Objects with priorities and a references to other objects:
         *      i1          i2
         *     /  \        /
         *    i3   i6    i4
         *   /    /  \
         *  i5   i7  i8
         *          /
         *         i9
         *
         * Goal: traverse the tree in starting from the element with lowest prio through all its children, among children traverse starting with the element with lowest prio
         *
         * Expected:
         * i1, i6, i7, i8, i9, i3, i5, i2, i4
         */
        Item i1 = new Item(1, 1);
        Item i2 = new Item(2, 2);
        Item i3 = new Item(3, 4, i1);
        Item i4 = new Item(4, 4, i2);
        Item i5 = new Item(5, 4, i3);
        Item i6 = new Item(6, 3, i1); // <- i6 should come before i3 in the result list
        Item i7 = new Item(7, 4, i6);
        Item i8 = new Item(8, 4, i6);
        Item i9 = new Item(9, 4, i8);

        List<Item> lst = Arrays.asList(i1, i2, i3, i4, i5, i6, i7, i8, i9);
        System.out.println(lst);

        /*lst = new LinkedList<Item>(lst);
        Comparator<Item> comp = (Item o1, Item o2) -> (Integer.compare(o1.getPrio(), o2.getPrio()));
        List<Item> children = new LinkedList<>();
        while (!lst.isEmpty()) {
            Item first = lst.stream()
                            .sorted(comp)
                            .findAny()
                            .orElse(null);
            children.add(first);
            lst.remove(first);
            children.addAll(popChildren(first, lst));
        }*/

        //System.out.println(children);
        //assert (children.equals(Arrays.asList(i1, i6, i7, i8, i9, i3, i5, i2, i4)));

        System.out.println(traverse(lst, Item::getRefItem, Comparator.comparingInt(Item::getPrio)));
        System.out.println(traverseStartingAtRoots(lst, Item::getRefItem, Comparator.comparingInt(Item::getPrio)));
    }

    private static List<Item> popChildren(Item parent, List<Item> items) {
        List<Item> rez = new ArrayList<Item>();
        items.stream()
             .filter(i -> parent.equals(i.getRefItem()))
             .sorted((Item o1, Item o2) -> (Integer.compare(o1.getPrio(), o2.getPrio())))
             .forEach(i -> {
                 rez.add(i);
                 items.remove(i);
                 rez.addAll(popChildren(i, items));
             });
        return rez;
    }

    static <T> List<T> traverse(List<T> elements, Function<T, T> parentMapper, Comparator<T> comparator) {
        // priority queue will sort the elements by using a comparator (by prio asc)
        // this will get us the lowest prio element and sort them
        PriorityQueue<T> pq = new PriorityQueue<>(comparator);
        Map<T, PriorityQueue<T>> g = new HashMap<>();
        for (T e : elements) {
            // adding element to priority queue
            pq.add(e);
            // just to avoid NP-checks setting element to have empty children
            g.put(e, new PriorityQueue<>(comparator));

            // saving parent to element relation also in a priority queue to have children already sorted by priority
            T parent = parentMapper.apply(e);
            if (parent != null) {
                g.computeIfAbsent(parent, k -> new PriorityQueue<>(comparator))
                 .add(e);
            }
        }

        Queue<T> q = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();
        if (!pq.isEmpty()) {
            // adding to simple queue the lowest prio element
            q.add(pq.poll());
        }

        List<T> result = new ArrayList<>();
        while (!q.isEmpty()) {
            T v = q.poll();

            // this is to check whether we have already processed the element
            // because in the next lines the full priority queue is used to get next elements
            // saving non visited elements into the result
            // because:
            // 1. on the start it will be taken from the full priority queue and it is for sure the first one
            // 2. on next iterations processing checks element's children and will add lowest prio child to be processed
            if (!visited.contains(v)) {
                result.add(v);
            }

            visited.add(v);

            PriorityQueue<T> children = g.get(v);
            if (!children.isEmpty()) {
                // we poll the lowest prio element from the sorted by prio children
                // just taking the first cause we will process the remaining by getting the parent of the child
                q.add(children.poll());
            } else {
                // if element has no children we need check its parent to have also no children
                // but if there is we poll with the lowest prio and add it for processing
                // if parent has also already empty children just saving the parent for processing to get one level-up
                // parent will not be added to result since it was processed already and is in the visited set
                T parent = parentMapper.apply(v);
                if (parent != null) {
                    PriorityQueue<T> parentChildren = g.get(parent);
                    if (!parentChildren.isEmpty()) {
                        q.add(parentChildren.poll());
                    } else {
                        q.add(parent);
                    }
                }
            }

            // if full tree is processed and simple queue didn't change
            // we use the next element from the full priority queue
            // if the element from the full prio queue is already processed we will just go to the next one
            if (q.isEmpty() && !pq.isEmpty()) {
                q.add(pq.poll());
            }
        }
        return result;
    }

    static <T> List<T> traverseStartingAtRoots(List<T> elements, Function<T, T> parentMapper, Comparator<T> comparator) {
        PriorityQueue<T> pq = new PriorityQueue<>(comparator);
        Map<T, PriorityQueue<T>> g = new HashMap<>();
        for (T e : elements) {
            g.put(e, new PriorityQueue<>(comparator));

            T parent = parentMapper.apply(e);
            if (parent != null) {
                g.computeIfAbsent(parent, k -> new PriorityQueue<>(comparator))
                 .add(e);
            } else {
                pq.add(e);
            }
        }

        Deque<T> q = new ArrayDeque<>(pq);
        List<T> tmp = new ArrayList<>();

        List<T> result = new ArrayList<>();
        while (!q.isEmpty()) {
            T v = q.poll();

            result.add(v);

            PriorityQueue<T> children = g.get(v);

            tmp.clear();
            tmp.addAll(children);
            tmp.addAll(q);

            q.clear();
            q.addAll(tmp);
        }
        return result;
    }
}