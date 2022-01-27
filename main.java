import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Problem {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class Student implements Comparable<Student> {

        private int id;
        private String name;
        private double cgpa;

        public Student(int id, String name, double cgpa) {
            this.id = id;
            this.name = name;
            this.cgpa = cgpa;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public double getCGPA() {
            return this.cgpa;
        }

        public int compareTo(Student o) {
            if (this.getCGPA() != o.getCGPA()) {
                if (this.getCGPA() > o.getCGPA())
                    return -1;
                return +1;
            } else if (this.getName().compareTo(o.getName()) != 0) {
                return this.getName().compareTo(o.getName());
            } else if (this.getId() != o.getId()) {
                if (this.getId() < o.getId())
                    return -1;
                return +1;
            }
            return 0;
        }
    }

    static class Priorities {
        public List<Student> getStudents(List<String> events) throws IOException {

            // Given
            // n: events.size() and assuming comparisson time to be constant
            // Time Complexity: O(n log n)
            // Space Complexity: O(n)

            List<Student> order = new ArrayList<Student>();
            PriorityQueue<Student> orderedStudents = new PriorityQueue<Student>();

            for (String event : events) {
                String[] splitted = event.split(" ");
                String enterCommand = "ENTER";
                if (splitted[0].equals(enterCommand)) {
                    orderedStudents.add(
                            new Student(Integer.parseInt(splitted[3]), splitted[1], Double.parseDouble(splitted[2])));
                } else {
                    orderedStudents.poll();
                }
            }

            while (orderedStudents.size() > 0) {
                order.add(orderedStudents.poll());
            }

            return order;
        }
    }

    static FastScanner sc = new FastScanner();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String args[]) throws IOException {

        int eventsAmount = sc.nextInt();
        List<String> events = new ArrayList<String>(eventsAmount);
        for (int i = 0; i < eventsAmount; i++) {
            String line = sc.br.readLine();
            events.add(line);
        }

        List<Student> answer = new Priorities().getStudents(events);

        if (answer.size() == 0) {
            bw.write("EMPTY\n");
        } else {
            for (Student student : answer) {
                bw.write(student.getName() + '\n');
            }
        }

        bw.flush();
        bw.close();
    }

    public static int inputInt() throws IOException {
        return sc.nextInt();
    }

    public static long inputLong() throws IOException {
        return sc.nextLong();
    }

    public static void print(String a) throws IOException {
        bw.write(a);
    }

    public static void printSp(String a) throws IOException {
        bw.write(a + " ");
    }

    public static void println(String a) throws IOException {
        bw.write(a + "\n");
    }
}