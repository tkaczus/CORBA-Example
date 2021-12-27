import EchoApp.EchoPOA;

public class EchoServer extends EchoPOA {
    @Override
    public int PIK_OtworzSesje() {
        return 2598;
    }

    @Override
    public int PIK_WybierzSerwer() {
        return 1;
    }

    @Override
    public String PIK_SprawdzSerwer() {
        return  "2598 <-- SS.Przetwarzanie,3,0\n" +
                "2598 <-- SS.PetlaKsiegujacaBlad,3,\n" +
                "2598 <-- SS.PetlaKsiegujacaStan,3,0\n" +
                "2598 <-- SS.JednostkaZModulo,3,Nie\n" +
                "2598 <-- SS.TabelaKursow,3,NBP\n" +
                "2598 <-- SS.MaxLiczbaProcesow,3,1\n" +
                "2598 <-- SS.MinLiczbaProcesow,3,1\n" +
                "2598 <-- SS.TrybPracy,3,Wieloprocesowy\n" +
                "2598 <-- SS.LOG_AWARYJNY,3,logi_defapi/CUI-INT-3_DEF1/log_aw_20211227_1_1.txt\n" +
                "2598 <-- SS.OMNIORB_USEHOSTNAME,3,Brak\n" +
                "2598 <-- SS.InitialHost,3,172.20.51.115 \n" +
                "2598 <-- SS.InitialPort,3,172.20.51.127\n" +
                "2598 <-- SS.OMNIORB_CONFIG,3,./orb.cfg\n" +
                "2598 <-- SS.VVTERM,3,putty\n" +
                "2598 <-- SS.VVTERMCAP,3,vvtermcap\n" +
                "2598 <-- SS.LIBRARY_PATH,3,.:/opt/omniORB_280/lib/i586_linux_2.0_glibc\n" +
                "2598 <-- SS.Katalog,3,/ctree/DSO/CUI-INT-3/DEF1/client\n" +
                "2598 <-- SS.CzasStartu,3,11:29:25\n" +
                "2598 <-- SS.DataStartu,3,2021.09.10\n" +
                "2598 <-- SS.InterwalKontroli,3,1\n" +
                "2598 <-- SS.Port,3,3552\n" +
                "2598 <-- SS.WersjaPro,3,defpcui  2.42.00a/09[2882]\n" +
                "2598 <-- SS.Wersja,3,4.76.00.0a09\n" +
                "2598 <-- SS.JEDNOSTKA_BANKU,3,1\n" +
                "2598 <-- SS.Nazwa,3,CUI-INT-3_DEF1\n" +
                "2598 <-- SS.Baza,3,CUI-INT-3_DEF1,ADMIN,ADMIN\n" +
                "2598 <-- SS.DataSystemowa,3,2021.12.27\n" +
                "2598 <-- SS.DataKsiegowania,3,2021.12.14";
    }
}
