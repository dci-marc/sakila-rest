## 🔹 Java Standard Library – Concrete Implementations

| Implementation            | Characteristics                                                              | Example Use Case                        |
|---------------------------|------------------------------------------------------------------------------|-----------------------------------------|
| **ArrayList**             | Resizable array, fast random access, slower inserts/removes in middle        | Product catalog displayed in UI         |
| **LinkedList**            | Doubly linked list, fast insert/remove at ends, slower random access         | Queue of tasks                          |
| **CopyOnWriteArrayList**  | Thread-safe, copy on modification, optimized for frequent reads, rare writes | Listener registry in multithreaded apps |
| **Vector** (legacy)       | Synchronized resizable array, slower than modern alternatives                | Legacy APIs requiring `Vector`          |
| **Stack** (legacy)        | Subclass of `Vector`, LIFO stack operations                                  | Undo history                            |
| **HashSet**               | Backed by `HashMap`, no duplicates, no order                                 | Unique user IDs                         |
| **LinkedHashSet**         | HashSet with predictable insertion order                                     | Cache with predictable iteration order  |
| **EnumSet**               | High-performance set specialized for enums, stored as bit vectors            | Days of week flags                      |
| **CopyOnWriteArraySet**   | Thread-safe set, backed by `CopyOnWriteArrayList`                            | Small set of subscribers                |
| **TreeSet**               | Sorted set, based on `TreeMap`, requires natural order or comparator         | Sorted usernames                        |
| **HashMap**               | Hash-based key-value store, allows one null key and null values              | Config properties                       |
| **LinkedHashMap**         | Predictable iteration order (insertion or access order)                      | LRU cache implementation                |
| **WeakHashMap**           | Entries removed when key is no longer strongly referenced                    | Metadata cache tied to objects          |
| **IdentityHashMap**       | Keys compared by reference (`==`), not `equals()`                            | Object graph processing                 |
| **EnumMap**               | High-performance map with enum keys, stored as arrays                        | State machine transitions               |
| **Hashtable** (legacy)    | Synchronized hash table                                                      | Legacy APIs                             |
| **Properties** (legacy)   | Subclass of `Hashtable`, string-only keys/values, for config files           | Java `.properties` files                |
| **TreeMap**               | Sorted map, natural/comparator order, navigable                              | Sorted dictionary                       |
| **ConcurrentHashMap**     | Highly concurrent hash map, thread-safe, lock striping                       | Shared caches in multithreaded systems  |
| **ConcurrentSkipListMap** | Thread-safe sorted map, skip-list based                                      | Concurrent sorted index                 |

## 🔹 Guava – Concrete Implementations

| Implementation                  | Characteristics                                               | Example Use Case                        |
|---------------------------------|---------------------------------------------------------------|-----------------------------------------|
| **HashMultiset**                | Hash-based multiset, counts duplicates                        | Word frequency counter                  |
| **LinkedHashMultiset**          | HashMultiset with predictable iteration order                 | Ordered inventory counts                |
| **TreeMultiset**                | Sorted multiset (like TreeSet but counts)                     | Leaderboard with duplicate scores       |
| **ImmutableMultiset**           | Immutable multiset                                            | Constant word frequencies               |
| **ConcurrentHashMultiset**      | Thread-safe multiset                                          | Concurrent event counter                |
| **ArrayListMultimap**           | Keys map to `ArrayList` values                                | Student → enrolled courses              |
| **HashMultimap**                | Keys map to `HashSet` values, avoids duplicate values per key | Tag → documents                         |
| **LinkedListMultimap**          | Keys map to `LinkedList` values, preserves insertion order    | Ordered multimap for history            |
| **LinkedHashMultimap**          | Keys map to `LinkedHashSet` values, predictable iteration     | Categories → products                   |
| **TreeMultimap**                | Keys and values sorted                                        | Index with sorted keys and values       |
| **ImmutableMultimap**           | Immutable multimap (key to values)                            | Static data relationships               |
| **ImmutableListMultimap**       | Immutable, preserves value order                              | Fixed lookup table with ordered results |
| **ImmutableSetMultimap**        | Immutable, avoids duplicate values per key                    | Immutable role → users mapping          |
| **HashBiMap**                   | Hash-based BiMap, unique keys and values                      | Username ↔ UserID                       |
| **EnumBiMap**                   | Enum keys or values, highly efficient                         | Enum ↔ String lookup                    |
| **ImmutableBiMap**              | Immutable BiMap                                               | Fixed country codes                     |
| **HashBasedTable**              | Backed by nested HashMaps                                     | Matrix-like data, e.g. seats in cinema  |
| **TreeBasedTable**              | Sorted rows and columns                                       | Sorted 2D grid (e.g. chess board)       |
| **ImmutableTable**              | Immutable table                                               | Static lookup grids                     |
| **MutableClassToInstanceMap**   | Stores instances keyed by their class, mutable                | Dependency registry                     |
| **ImmutableClassToInstanceMap** | Immutable version of above                                    | Static type → instance mapping          |
| **TreeRangeSet**                | Mutable, stores merged continuous ranges                      | IP address blocking                     |
| **ImmutableRangeSet**           | Immutable range set                                           | Constant allowed ranges                 |
| **TreeRangeMap**                | Mutable map from ranges to values                             | Tax brackets by income                  |
| **ImmutableRangeMap**           | Immutable range map                                           | Static mapping of ranges                |
