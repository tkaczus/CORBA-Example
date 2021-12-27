import EchoApp.EchoPOA;

public class EchoServer extends EchoPOA {
    @Override
    public String PIK_OtworzSesje() {
        return "PIK_OtworzSesje";
    }

    @Override
    public String PIK_SprawdzSerwer() {
        return  "SS.Przetwarzanie,3,0\n" +
                "SS.PetlaKsiegujacaBlad,3,\n" +
                "SS.PetlaKsiegujacaStan,3,0\n" +
                "SS.JednostkaZModulo,3,Nie\n" +
                "SS.TabelaKursow,3,NBP\n" +
                "SS.MaxLiczbaProcesow,3,1\n" +
                "SS.MinLiczbaProcesow,3,1\n" +
                "SS.TrybPracy,3,Wieloprocesowy\n" +
                "SS.LOG_AWARYJNY,3,logi_defapi/CUI-INT-3_DEF1/log_aw_20211227_1_1.txt\n" +
                "SS.OMNIORB_USEHOSTNAME,3,Brak\n" +
                "SS.InitialHost,3,172.20.51.115 \n" +
                "SS.InitialPort,3,172.20.51.127\n" +
                "SS.OMNIORB_CONFIG,3,./orb.cfg\n" +
                "SS.VVTERM,3,putty\n" +
                "SS.VVTERMCAP,3,vvtermcap\n" +
                "SS.LIBRARY_PATH,3,.:/opt/omniORB_280/lib/i586_linux_2.0_glibc\n" +
                "SS.Katalog,3,/ctree/DSO/CUI-INT-3/DEF1/client\n" +
                "SS.CzasStartu,3,11:29:25\n" +
                "SS.DataStartu,3,2021.09.10\n" +
                "SS.InterwalKontroli,3,1\n" +
                "SS.Port,3,3552\n" +
                "SS.WersjaPro,3,defpcui  2.42.00a/09[2882]\n" +
                "SS.Wersja,3,4.76.00.0a09\n" +
                "SS.JEDNOSTKA_BANKU,3,1\n" +
                "SS.Nazwa,3,CUI-INT-3_DEF1\n" +
                "SS.Baza,3,CUI-INT-3_DEF1,ADMIN,ADMIN\n" +
                "SS.DataSystemowa,3,2021.12.27\n" +
                "SS.DataKsiegowania,3,2021.12.14";
    }
}
