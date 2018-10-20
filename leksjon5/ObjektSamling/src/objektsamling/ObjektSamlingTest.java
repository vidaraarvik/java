package objektsamling;

public class ObjektSamlingTest {

    public static void main(String[] args) {
        ObjektSamling personer = new ObjektSamling(50);
        Person p1 = new Person(1, "Per");
        Person p2 = new Person(2, "Kari");
        
        personer.settInn(p1);
        personer.settInn(p2);
        
        Person p = (Person) personer.finn("1");
        System.out.println(p);
        
        
    }

}
