import java.util.*;
import java.util.stream.Collectors;

        /*

          +---+
         /   /|
        +---+ |
        |   | +
        |   |/
        +---+

        */

/*
5 5 4
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
3 3
2 2 4 2
4 2 4 4
4 4 2 4
2 4 2 2
2 5 3 3

output = 10

6 2 2
0 0 1 0 0 0
0 0 1 0 0 0
0 0 0 1 0 0
0 0 0 1 0 0
0 0 0 0 1 0
0 0 0 1 0 0
6 5
6 6 5 6
4 6 4 5

output = 2

5 4 20
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
3 3
1 1 5 5
1 5 5 5
5 5 5 1
5 1 5 5

output = 32
 */
public class Main {
    // TODO 출발지랑 도착지가 겹쳐있을 수도 있다
    // TODO 1번 택시 도착지는 1, 1
    // TODO 2번 택시 출발지는 1, 1 일 경우
    // TODO 구현해야할 목록 : 현재 내 택시위치에 손님 있을 경우 (이게 아마도 제일 문제 일 거같다)
    // TODO 수정해야할 목록 ? : 139 라인 SPACE 이거는 아닐수도 있지만 혹시 몰라서 일단 작성 해 두었다 (안틀렷을 확률 99%)

    static Scanner sc = new Scanner(System.in);
    static int n, m, gas, x, y;
    static int[][] graph = new int[22][22];
    static List<Customer> customers = new ArrayList<>();
    static final int SPACE = 0;
    static final int WALL = 1;
    static final int CUSTOMER = 2;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void init() {
        n = sc.nextInt();
        m = sc.nextInt();
        gas = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        x = sc.nextInt() - 1;
        y = sc.nextInt() - 1;
        for (int i = 0; i < m; i++) {
            int customerX, customerY, destinationX, destinationY;
            customerX = sc.nextInt() - 1;
            customerY = sc.nextInt() - 1;
            graph[customerX][customerY] = CUSTOMER;
            destinationX = sc.nextInt() - 1;
            destinationY = sc.nextInt() - 1;
            customers.add(new Customer(customerX, customerY, destinationX, destinationY));
        }
    }
    static boolean isExistWall(int nx, int ny) {
        return graph[nx][ny] == WALL;
    }
    static boolean isBound(int nx, int ny) {
        return 0 <= nx && nx < n && 0 <= ny && ny < n;
    }
    static boolean isExistCustomer(int nx, int ny) {
        return graph[nx][ny] == CUSTOMER;
    }
    static boolean isNotVisited(int[][] distance, int nx, int ny) {
        return distance[nx][ny] == -1;
    }
    static boolean isDestination(int nx, int ny, Customer customer) {
        return nx == customer.destinationCoordinate.first && ny == customer.destinationCoordinate.second;
    }
    static void updateShortRouteInfo(Customer customer, int[][] distance, List<Customer> shortestRouteInfo) {
        customer.updateDistance(distance);
        shortestRouteInfo.add(customer);
    }
    static List<Customer> shortestRouteSearch(Taxi taxi, Customers customerInfo) {
        int[][] distance = new int[22][22];
        for (int i = 0; i < 22; i++) Arrays.fill(distance[i], -1);

        List<Customer> shortestRouteInfo = new ArrayList<>();
        Coordinate taxiCoordinate = taxi.coordinate;
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(taxiCoordinate);
        distance[taxiCoordinate.first][taxiCoordinate.second] = 0;

        if (isExistCustomer(taxiCoordinate.first, taxiCoordinate.second)) {
            Customer customer = customerInfo.find(taxiCoordinate.first, taxiCoordinate.second);
            updateShortRouteInfo(customer, distance, shortestRouteInfo);
        }
        while (!queue.isEmpty()) {
            int x, y;
            Coordinate coordinate = queue.poll();
            x = coordinate.first;
            y = coordinate.second;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isBound(nx, ny) && !isExistWall(nx, ny) && isNotVisited(distance, nx, ny)) {
                    distance[nx][ny] = distance[x][y] + 1;
                    if (isExistCustomer(nx, ny)) {
                        Customer customer = customerInfo.find(nx, ny);
                        updateShortRouteInfo(customer, distance, shortestRouteInfo);
                    }
                    queue.offer(new Coordinate(nx, ny));
                }
            }
        }
        return shortestRouteInfo;
    }
    static List<Customer> routeInfoSort(List<Customer> shortRouteInfo) {
        return shortRouteInfo.stream()
                .sorted(
                        Comparator.comparing(Customer::getDistance)
                                .thenComparing((customer) -> customer.getCoordinate().first)
                                .thenComparing((customer) -> customer.getCoordinate().second))
                .collect(Collectors.toList());


    }

    static boolean isUnableToPickupCustomer(List<Customer> shortestRouteInfo) {
        return shortestRouteInfo.size() == 0;
    }
    static int run(Taxi taxi, Customers customerInfo) {
        while (m-- > 0) {
            List<Customer> shortestRouteInfo = shortestRouteSearch(taxi, customerInfo);
            if (isUnableToPickupCustomer(shortestRouteInfo)) return -1;
            List<Customer> sortRouteInfo = routeInfoSort(shortestRouteInfo);
            customerInfo = new Customers(sortRouteInfo);
            if (!isMove(taxi, customerInfo)) return -1;
        }
        return taxi.gas;
    }
    static boolean isDestinationEqualsStartingPoint(Taxi taxi, Customer customer) {
        Coordinate taxiCoordinate = taxi.coordinate;
        Coordinate customerDestination = customer.destinationCoordinate;
        return taxiCoordinate.first == customerDestination.first && taxiCoordinate.second == customerDestination.second;

    }
    static boolean moveToCustomerDestination(Taxi taxi, Customer customer) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(taxi.coordinate);
        int[][] distance = new int[22][22];
        for (int i = 0; i < 22; i++) Arrays.fill(distance[i], -1);
        distance[taxi.coordinate.first][taxi.coordinate.second] = 0;
        if (isDestinationEqualsStartingPoint(taxi, customer)) {
            graph[customer.coordinate.first][customer.coordinate.second] = SPACE;
            return true;
        }
        while (!queue.isEmpty()) {
            int x, y;
            Coordinate curCoordinate = queue.poll();
            x = curCoordinate.first;
            y = curCoordinate.second;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isBound(nx, ny) && !isExistWall(nx, ny) && isNotVisited(distance, nx, ny)) {
                    distance[nx][ny] = distance[x][y] + 1;
                    if (taxi.gas < distance[nx][ny]) return false;
                    if (isDestination(nx, ny, customer)) {
                        taxi.arrive(nx, ny, distance[nx][ny]);
                        graph[customer.coordinate.first][customer.coordinate.second] = SPACE;
                        return true;
                    }
                    queue.offer(new Coordinate(nx, ny));
                }
            }
        }
        return false;
    }
    static boolean isMove(Taxi taxi, Customers customerInfo) {
        Customer customer = customerInfo.get(0);
        if (!taxi.useGas(customer.distance)) return false;
        taxi.coordinate = customer.coordinate;
        if (!moveToCustomerDestination(taxi, customer)) return false;
        customerInfo.remove(customer);
        return true;
    }

    public static void main(String[] args) {
        init();
        Taxi taxi = Taxi.of(x, y, gas);
        Customers customerInfo = new Customers(customers);
        System.out.println(run(taxi, customerInfo));


    }
}
class Taxi {
    Coordinate coordinate;
    int gas;
    Taxi(int x, int y, int gas) {
        this.coordinate = new Coordinate(x, y);
        this.gas = gas;
    }
    static Taxi of(int x, int y, int gas) {
        return new Taxi(x, y, gas);
    }

    boolean useGas(int distance) {
        if (gas - distance < 0) return false;
        gas -= distance;
        return true;

    }
    void chargeGas(int distance) {
        gas += (distance * 2);
    }
    void arrive(int nx, int ny, int distance) {
        useGas(distance);
        chargeGas(distance);
        coordinate = new Coordinate(nx, ny);
    }
}

class Customers {
    List<Customer> customers;
    Customers(List<Customer> customers) {
        this.customers = customers;
    }
    @Override
    public String toString() {
        return customers.toString();
    }
    Customer find(int nx, int ny) {
        return customers.stream()
                .filter((customer) -> customer.find(nx, ny))
                .findAny()
                .orElse(null);
    }
    Customer get(int idx) {
        return customers.get(idx);
    }
    void remove(Customer customer) {
        customers.remove(customer);
    }
}
class Customer {
    Coordinate coordinate;
    Coordinate destinationCoordinate;
    int distance;
    Customer(int x, int y, int destinationX, int destinationY) {
        this.coordinate = new Coordinate(x, y);
        this.destinationCoordinate = new Coordinate(destinationX, destinationY);
    }
    boolean find(int nx, int ny) {
        return coordinate.first == nx && coordinate.second == ny;
    }
    void updateDistance(int[][] distance) {
        this.distance = distance[coordinate.first][coordinate.second];
    }
    int getDistance() {
        return distance;
    }
    Coordinate getCoordinate() {
        return coordinate;
    }
    @Override
    public String toString() {
        return coordinate + " " + "" +distance;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Customer) {
            Customer customer = (Customer) obj;
            return coordinate.equals(customer.coordinate) && destinationCoordinate.equals(customer.destinationCoordinate);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(coordinate, destinationCoordinate);
    }

}

class Node {
    char root;
    Node left = null;
    Node right = null;

    Node(char root) {
        this.root = root;
    }
}

class Coordinate implements Comparable<Coordinate> {
    int first;
    int second;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first + " " + second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return first == coordinate.first && second == coordinate.second;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public int compareTo(Coordinate o) {
        return second - o.second;
    }
}