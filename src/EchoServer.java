import EchoApp.EchoPOA;

public class EchoServer extends EchoPOA {
    @Override
    public int PIK_OtworzSesje(org.omg.CORBA.portable.InputStream in) {
        return 1;
    }

    @Override
    public int PIK_WybierzSerwer(org.omg.CORBA.portable.InputStream in) {
        return 1;
    }

    @Override
    public String PIK_ZeZmiennej(org.omg.CORBA.portable.InputStream in) {
        return "2021.12.14";
    }

    @Override
    public int PIK_SprawdzSerwer(org.omg.CORBA.portable.InputStream in) {
        return  1;
    }
}
