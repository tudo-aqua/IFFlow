import java.lang.reflect.Field;
import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    public static void main(String[] args) throws Exception{
        DataStorage data = new DataStorage();
        PasswordStorage pwd = new PasswordStorage();

        Field pwdField = PasswordStorage.class.getDeclaredField("password");
        pwdField.setAccessible(true);

        Field dataField = DataStorage.class.getDeclaredField("data");
        dataField.setAccessible(true);

        dataField.set(data, pwdField.get(pwd).toString());
        Tainting.check(data.printData(), IFSPEC);
        Tainting.stopAnalysis();
    }

}
