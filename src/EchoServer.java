import EchoApp.EchoPOA;

public class EchoServer extends EchoPOA {
    @Override
    public int PIK_OtworzSesje(org.omg.CORBA.portable.InputStream in) {
        return 1;
    }

    @Override
    public int PIK_WybierzSerwer() {
        return 1;
    }

    @Override
    public String PIK_ZeZmiennej() {
        return "2021.12.14";
    }

    @Override
    public int PIK_SprawdzSerwer() {
        return  1;
    }
}
