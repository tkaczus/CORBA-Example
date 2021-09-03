import EchoApp.EchoPOA;

public class EchoServer extends EchoPOA {
    @Override
    public String echoString() {
        return "SS.Przetwarzanie,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.PetlaKsiegujacaBlad,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.PetlaKsiegujacaStan,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.JednostkaZModulo,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.TabelaKursow,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.MaxLiczbaProcesow,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.MinLiczbaProcesow,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.TrybPracy,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.LOG_AWARYJNY,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.OMNIORB_USEHOSTNAME,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.InitialHost,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.InitialPort,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.OMNIORB_CONFIG,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.VVTERM,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.VVTERMCAP,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.LIBRARY_PATH,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.Katalog,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.CzasStartu,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.DataStartu,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.InterwalKontroli,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.Port,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.WersjaPro,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.Wersja,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.JEDNOSTKA_BANKU,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.Nazwa,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.Baza,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.DataSystemowa,3,\n" +
                "2021.09.03  0:15:29     24978 --> SS.DataKsiegowania,3,\n" +
                "2021.09.03  0:15:29     24978 <-- SS.Przetwarzanie,3,0\n" +
                "2021.09.03  0:15:29     24978 <-- SS.PetlaKsiegujacaBlad,3,\n" +
                "2021.09.03  0:15:29     24978 <-- SS.PetlaKsiegujacaStan,3,0\n" +
                "2021.09.03  0:15:29     24978 <-- SS.JednostkaZModulo,3,Nie\n" +
                "2021.09.03  0:15:29     24978 <-- SS.TabelaKursow,3,BPS\n" +
                "2021.09.03  0:15:29     24978 <-- SS.MaxLiczbaProcesow,3,5\n" +
                "2021.09.03  0:15:29     24978 <-- SS.MinLiczbaProcesow,3,1\n" +
                "2021.09.03  0:15:29     24978 <-- SS.TrybPracy,3,Wieloprocesowy\n" +
                "2021.09.03  0:15:29     24978 <-- SS.LOG_AWARYJNY,3,logi_defapi/Muszdso3/log_aw_20210903_6_1.txt\n" +
                "2021.09.03  0:15:29     24978 <-- SS.OMNIORB_USEHOSTNAME,3,Brak\n" +
                "2021.09.03  0:15:29     24978 <-- SS.InitialHost,3,172.20.51.115 \n" +
                "2021.09.03  0:15:29     24978 <-- SS.InitialPort,3,172.20.51.127\n" +
                "2021.09.03  0:15:29     24978 <-- SS.OMNIORB_CONFIG,3,./orb.cfg\n" +
                "2021.09.03  0:15:29     24978 <-- SS.VVTERM,3,putty\n" +
                "2021.09.03  0:15:29     24978 <-- SS.VVTERMCAP,3,vvtermcap\n" +
                "2021.09.03  0:15:29     24978 <-- SS.LIBRARY_PATH,3,.:/opt/omniORB_280/lib/i586_linux_2.0_glibc\n" +
                "2021.09.03  0:15:29     24978 <-- SS.Katalog,3,/ctree/DSO/MuszDSO3/client\n" +
                "2021.09.03  0:15:29     24978 <-- SS.CzasStartu,3,16:07:29\n" +
                "2021.09.03  0:15:29     24978 <-- SS.DataStartu,3,2021.03.11\n" +
                "2021.09.03  0:15:29     24978 <-- SS.InterwalKontroli,3,1\n" +
                "2021.09.03  0:15:29     24978 <-- SS.Port,3,3517\n" +
                "2021.09.03  0:15:29     24978 <-- SS.WersjaPro,3,defpcui  2.40.08a/02[2730]\n" +
                "2021.09.03  0:15:29     24978 <-- SS.Wersja,3,4.74.08.0a01\n" +
                "2021.09.03  0:15:29     24978 <-- SS.JEDNOSTKA_BANKU,3,1\n" +
                "2021.09.03  0:15:29     24978 <-- SS.Nazwa,3,Muszdso3\n" +
                "2021.09.03  0:15:29     24978 <-- SS.Baza,3,Muszdso3,ADMIN,ADMIN\n" +
                "2021.09.03  0:15:29     24978 <-- SS.DataSystemowa,3,2021.09.03\n" +
                "2021.09.03  0:15:29     24978 <-- SS.DataKsiegowania,3,2018.04.04";
    }
}
