package reflection;


public class Test {

    private int id;
    private String name;

    public Test(int id) {
        this.id = id;
    }

    public Test(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Test() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test)) return false;

        Test test = (Test) o;

        if (id != test.id) return false;
        return name != null ? name.equals(test.name) : test.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
