package test;

import com.asseco.defapi.defAPI;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefApi_R_BS_EXE_5x {
    //REGRES
    // API_PobierzListeOkresow
    // API_PobierzListeProduktow
    // API_Pobierz_LTK
    // API_Pobierz_UBK
    // API_UmowaLimituWRB
    // ObslugaPZwgPSD
    // PIK_WykonajZlecenie
    // PSD
    // PSD2
    // RejestrZdarzen
    // ZakladanieRB_defAPI
    
    static defAPI api;
    static int idSesji;
    static final int WYNIK_OK = 1;
    static final int WYNIK_ERR = 0;

    static String CORBA = "";
    static String omniIP = "";
    static String omniPORT = "";
    static String user = "SERWIS";
    static String userPASS = "";
    static String idSystem = "CUI";
    
    int fNr = 0; 
    static int iloscF = 0;
    
    String DataKsiegowaDef = ""; //Data księgowa
    String DataKsiegowaDefP1 = ""; //Data księgowa plus jeden dzień
    String DataKsiegowaDefP2 = ""; //Data księgowa plus dwa dni
    String DataKsiegowaDefM1 = ""; //Data księgowa minus jeden dzień
    String DataKsiegowaDefP1M = ""; //Data księgowa plus jeden miesiąc
    String DataKsiegowaDefP1P1M = ""; //Data księgowa plus jeden dzień plus jeden miesiąc
    String DataKsiegowaDefP1P2M = ""; //Data księgowa plus jeden dzień plus jeden miesiąc
    String DataKsiegowaDefP1P1R = ""; //Data księgowa plus jeden dzień plus jeden rok
    String DataKsiegowaDefP1P1MP1R = ""; //Data księgowa plus jeden dzień plus jeden miesiąc plus jeden rok
    String DataKsiegowaDefP1R = ""; //Data księgowa plus jeden rok
    
    String NRBSrodki = "";
    
    String FBaza = "";
    String FWersjaDef = "";
    String FWersjaExe = "";
    String FKatalog = "";
    
    int LongSleep = 180000; //180000
    int ShortSleep = 15000; //15000
    String TeamsWebHookURL="https://outlook.office.com/webhook/e203a253-e435-4dab-bd99-bbb1927b0904@88152bde-cfa3-4a5c-b981-a785c624bb42/IncomingWebhook/4e27b0f1d69c41aeaddda2c20ea86a82/36be799e-ab33-4e76-ae9e-9d3ecbb794c4";
    int fnErr=0;

    void akcja() throws FileNotFoundException, IOException{
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        ustaw();
        otworzPolaczenie();
        PIK_Klient3O(); //Dodanie nowego klienta        
    //Wymagane udostępnienie ZakladanieRB_defAPI
        PIK_UmowaRB1(); //Założenie RB 
        PIK_Rachunki5();
        PIK_UmowaKR1();
        PIK_UmowaKR2();
        PIK_UmowaKR4();
        PIK_UmowaKR13();
        PIK_Rachunki5_ID_AN_DEF_KR();
        PIK_WykonajProcedure_PobierzInfo_WKR();
        PIK_AutoryzacjaOnLine1(); //Zakładanie autoryzacji gdy są środki
        PIK_AutoryzacjaOnLine1X(); //Zakładanie autoryzacji gdy brak środków
        PIK_Autoryzacja7();
        PIK_Autoryzacja7_Typ10();
        PIK_DajListeAutoryzacji();
        PIK_WykonajZlecenie2_RozliczSZ();
        PIK_DajListeAutoryzacji();
        PIK_Autoryzacja7_Typ10();
        PIK_DajListeAutoryzacji();
        PIK_WpiszKomunikat2_RozliczSZ();
        PIK_DajListeAutoryzacji();
        PIK_Klient1();//Pobranie danych klienta
        PIK_Klient4(); //Modyfikacja klienta
        PIK_Klient2();//Pobranie listy zmian w kartotece klientów*/
        PIK_WykonajZlecenie_ZalozDepozyt();
    //Wymagane udostępnienie PIK_WykonajZlecenie
        PIK_WykonajZlecenie2(); //Przelew elixir ELX_RK_UZNANIE
        PIK_DajSaldo();
    //Wymagane udostępnienie PSD
        PIK_WpiszKomunikat0_OLD(); // ZUS po staremu
        PIK_WpiszKomunikat1_ZUS(); // ZUS
        PIK_WpiszKomunikat6_OLD();
        PIK_WpiszKomunikat6(); // US
        PIK_WpiszKomunikat1(); //Przelew elixir zwykły          
        PIK_PobierzKomunikat();
        PIK_WpiszPaczke(); // Zawiera PIK_DodajKomunikat Zew, US, ZUS(nowy)
    //Wymagane udostepnienie API_UmowaLimituWRB
        PIK_UmowaRB13(); //Załozenie limitu RB/ROR  
    //Wymagane udostępnienie ObslugaPZwgPSD
        PIK_UpowaznieniaPZ0(); //Dodanie upoważnienia PZ
        PIK_UpowaznieniaPZ3(); //Pobranie listy (ID) upoważnień PZ
        PIK_UpowaznieniaPZ1(); //Modydfikacja upoważnienia PZ
        PIK_UpowaznieniaPZ2(); //Usunięcie upoważnienia PZ
    //Wymagane udostępnienie API_Pobierz_LTK
        PIK_WykonajProcedure_PobierzLTK();
        PIK_WykonajProcedure_TestRachunku1();
    //Wymagane udostępnienie API_PobierzListeProduktow
        PIK_WykonajProcedure_PobierzListeProduktow1();
        PIK_WykonajProcedure_PobierzListeProduktow2();
    //Wymagane udostępnienie API_PobierzListeOkresow
        PIK_WykonajProcedure_PobierzListeOkresow();
    //Wymagane udostępnienie API_Pobierz_UBK
        PIK_WykonajProcedure_Pobierz_UBK();
    //Wymagane udostepnienei RejestrZdarzen
        PIK_Zdarzenia();
        PIK_DajKursyWalut();
        PIK_Kalendarz();
        PIK_Uprawnienia3();
        PIK_WykonajProcedure_PobierzInfo_CUR();
        PIK_WykonajProcedure_PobierzInfo_WSR();
    //Wymagane udostępnienie RBWyciagiWWW
        PIK_WykonajProcedure_ObslugaParWyciagu();
        PIK_WykonajProcedure_PobierzKosztyDyspozycji();
        PIK_WykonajProcedure_PobierzInfo_DEP();
        PIK_WykonajProcedure_PobierzInfo_KLD();
        PIK_WykonajProcedure_PobierzInfo_KLM();
        PIK_WykonajProcedure_PobierzInfo_KLI();
        PIK_WykonajProcedure_PobierzInfo_LKR();
        PIK_ZleceniaStale1();
        PIK_ZleceniaStale8();
        PIK_ZleceniaStale2();
        PIK_ZleceniaStale4();
        PIK_ZleceniaStale6();
    //Wymagane udostępnienie PSD2
        PIK_ZleceniaStale11();
        PIK_DajListeDekretow();
	PIK_Klient3_ROs(); //Dodanie klienta typu rolnik formatka osoby fizycznej
        PIK_Klient3_RF(); //Dodanie klienta typu rolnik formatka firmy
        //MPP Start
        PIK_Klient3F(); // zakłada klienta typu FIRMA dla obsługi rachunku MPP
        PIK_UmowaRB1_MPP(); //Dodaje rachunek klientowi z obsługą MPP i zakłada automatycznie nowy rachunek VAT
        PIK_DajRachunekBiezacy5(); //Odczytuje informacje o rachunkach klienta RB i MPP
        ZasilRBzMPP(); //Zasila RB klienta MPP
        PIK_UmowaRB1_MPP(); // Zakłada nowy rachunek MPP i przypisuje istniejący VAT
        PIK_DajRachunekBiezacy5(); //Odczytuje informacje o rachunkach klienta (aktualnie 3)
        PIK_WpiszKomunikatMPPWewSV(); //SV - Sam Vat
        PIK_PobierzKomunikat();
        PIK_WykonajZlecenieMPPWewKV(); //KV - Kwota + Vat
        PIK_PobierzKomunikat();
        PIK_WykonajZlecenieMPPZewKV();
        PIK_PobierzKomunikat();
        PIK_WpiszKomunikatMPPZewKV();
        PIK_PobierzKomunikat();
        PIK_WykonajZlecenieMPPZewSV();
        PIK_PobierzKomunikat();
        PIK_WpiszKomunikatMPPZewSV();
        PIK_PobierzKomunikat();
        PIK_WykonajZlecenieMPPUS();
        PIK_PobierzKomunikat();
        PIK_WpiszKomunikatMPPUS();
        PIK_PobierzKomunikat();
        PIK_DajDekrety();
        PIK_DajListeDekretow();
        PIK_Rachunki5();
        PIK_ZleceniaStale1MPPZew(); // Dodanie ZS MPP
        PIK_ZleceniaStale8();
        PIK_ZleceniaStale2MPP();
        PIK_ZleceniaStale6();
        PIK_ZleceniaStale1MPPWew();
        PIK_ZleceniaStale8();
        PIK_ZleceniaStale2MPP();
        PIK_ZleceniaStale6();
        //MPP Stop
        zakonczPolaczenie();
    }

    void separator(){
        System.out.println(); 
        System.out.println("---------------------------------------------------------------------------------------------------------------"); 
        System.out.println(); 
        try{
            Thread.sleep(ShortSleep);
        }catch (InterruptedException e){}
    }
    
    void ustawParametry(String[] parametry){
        if(parametry.length % 2 == 0){
            for (int i = 0; i<parametry.length; i=i+2){
                switch (parametry[i]){
                    case "-shortSleep":
                        ShortSleep = Integer.parseInt(parametry[i+1]);
                        break;
                    case "-longSleep":
                        LongSleep = Integer.parseInt(parametry[i+1]);
                        break;
                    case "-teamsWebHookURL":
                        TeamsWebHookURL = parametry[i+1];
                        break;
                    default:
                        System.out.println("Nieznany parametr - " + parametry[i] + "\n"
                                + "Znane parametry:\n"
                                + "-shortSleep\n"
                                + "-longSleep\n"
                                + "-teamsWebHookURL\n");
                }
            }
        }
    }
    
    void ustaw() throws IOException{
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String temp = "";
        System.out.println("Upewnij się że INTER.zawsze_biezace_sr = Tak"); 
        System.out.println("Wyczyść katalog logi_defapi");
        System.out.println("Uruchom serwer CORBA - corba_start");
        CORBA = wczytajZmienna("CORBA");
        System.out.println("Ostatnio użyta nazwa serwera CORBA to: " + CORBA + " enter aby pozostawić, -sn[nazwa] aby zmienić");
        temp = stdin.readLine();
        if(temp.length() > 0){
            CORBA = temp;
            zapiszZmienna("CORBA", CORBA);
        }
        CORBA = CORBA.substring(3);
        omniIP = wczytajZmienna("omniIP");
        System.out.println("Ostatnio użyty IP serwera OMNI to: " + omniIP + " enter aby pozostawić, XXX.XXX.XXX.XXX aby zmienić");
        temp = stdin.readLine();
        if(temp.length() > 0){
            omniIP = temp;
            zapiszZmienna("omniIP", omniIP);
        }
        omniPORT = wczytajZmienna("omniPORT");
        System.out.println("Ostatnio użyty PORT serwera OMNI to: " + omniPORT + " enter aby pozostawić, XXXX aby zmienić");
        temp = stdin.readLine();
        if(temp.length() > 0){
            omniPORT = temp;
            zapiszZmienna("omniPORT", omniPORT);
        }
        userPASS = wczytajZmienna("userPASS");
        System.out.println("Ostatnio użyte hasło na SERWIS to: " + userPASS + " enter aby pozostawić, XXXX aby zmienić");
        temp = stdin.readLine();
        if(temp.length() > 0){
            userPASS = temp;
            zapiszZmienna("userPASS", userPASS);
        }
        
        iloscF = Integer.parseInt(wczytajZmienna("IloscFkcji"));
        
        System.out.println("Uruchom pętlę księgującą i naciśnij enter");
        stdin.readLine();
        
        
    }
    
    String dko(String DK, int x, String jedn){
        //YYYY.MM.DD
        java.util.Calendar tmpDK = new java.util.GregorianCalendar(Integer.parseInt(DK.substring(0, 4)), Integer.parseInt(DK.substring(5, 7))-1, Integer.parseInt(DK.substring(8, 10)));
        String nDK = "";
        
        if(jedn == "d" || jedn == "D"){
            tmpDK.add(Calendar.DAY_OF_MONTH,x);
        }
        else if(jedn == "m" || jedn == "M"){
            tmpDK.add(Calendar.MONTH,x);
        }
        else if(jedn == "y" || jedn == "Y"){
            tmpDK.add(Calendar.YEAR, x);
        }
        
        nDK = "" + tmpDK.get(Calendar.YEAR) + ".";
        if((tmpDK.get(Calendar.MONTH)+1) < 10){
            nDK = nDK + "0" + (tmpDK.get(Calendar.MONTH)+1) + ".";
        }
        else{
            nDK = nDK + "" + (tmpDK.get(Calendar.MONTH)+1) + ".";
        }
        if(tmpDK.get(Calendar.DAY_OF_MONTH) < 10){
            nDK = nDK + "0" + tmpDK.get(Calendar.DAY_OF_MONTH);
        }
        else{
            nDK = nDK + "" + tmpDK.get(Calendar.DAY_OF_MONTH);
        }
        System.out.println(DK + " > " + nDK);
        return nDK;
    }
    
    String wczytajZmienna(String nazwa) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader("Pliki/" + nazwa + ".txt");
        BufferedReader br = new BufferedReader(fr);
        String s = "";
        String tmp = "";
        while((s = br.readLine()) != null){
            tmp=s;
        }
        br.close();
        fr.close();
        System.out.println("Wczytalem zmienną " + nazwa + " = " + tmp);
        return tmp;
    }
    
    void zapiszZmienna(String nazwa, String wartosc) throws IOException{
        FileWriter fw = new FileWriter("Pliki/" + nazwa + ".txt");
        fw.write(wartosc);
        fw.close();
    }
    
    void sendTeamsMessage(String message){
        try {
            String schema = "{\n" +
                "    '@context': 'https://schema.org/extensions',\n" +
                "    '@type': 'MessageCard',\n" +
                "    'themeColor': '00C672',\n" +
                "    'title': '%s',\n" +
                "    'text': '%s'\n" +
                "}";
            String msg=String.format(schema, "Uwaga - defApi!!!", message);
            String command = "c:\\curl\\bin\\curl.exe -H \"Content-Type: application/json\" -d \"" + msg + "\" " + TeamsWebHookURL;
//            String command = "c:\\curl\\bin\\curl.exe -H \"Content-Type: application/json\" -d \"{'text': '" + message + "'}\" " + TeamsWebHookURL;
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String response="";
            while ((line = reader.readLine()) != null) {
                response+=line;
            }
            process.destroy();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(DefApi_R_BS_EXE_5x.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public final void otworzPolaczenie() throws IOException
    {
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        api = new defAPI();
        idSesji = 0;
        String[] out = new String[1];
        
        wynik = api.PIK_PlikLogu("RegresDefApi_BS_EXE5x.txt", 2);
        System.out.println("PIK_PlikLogu   : " + wynik);
        
        idSesji = api.PIK_OtworzSesje(idSystem, user, userPASS);
        System.out.println("PIK_OtworssssssssssssssszSesje: " + idSesji);

        wynik = api.PIK_UstawUsluge(omniIP, omniPORT);
        System.out.println("PIK_UstawUsluge  : " + wynik + "\nidSesji: " + idSesji);
        
        wynik = api.PIK_WybierzSerwer(idSesji, CORBA, user, userPASS);
        System.out.println("PIK_WybierzSerwer  : " +  wynik);
        
        wynik = api.PIK_SprawdzSerwer(idSesji, CORBA, user, userPASS);
        System.out.println("PIK_SprawdzSerwer  : " +  wynik);

        api.PIK_ZeZmiennej (idSesji, "SS.DataKsiegowania", out);
        System.out.println("Data Księgowania    : " + out[0]);
        DataKsiegowaDef = out[0];
        if(DataKsiegowaDef.length() == 8){
            DataKsiegowaDef = DataKsiegowaDef.substring(0, 4) + "." + DataKsiegowaDef.substring(4, 6) + "." + DataKsiegowaDef.substring(6, 8);
        }
        DataKsiegowaDefP1 = dko(DataKsiegowaDef,+1,"d");
        DataKsiegowaDefP2 = dko(DataKsiegowaDef,+2,"d");
        DataKsiegowaDefM1 = dko(DataKsiegowaDef,-1,"d");
        DataKsiegowaDefP1M = dko(DataKsiegowaDef,+1,"m"); 
        DataKsiegowaDefP1P1M = dko(DataKsiegowaDefP1,+1,"m");
        DataKsiegowaDefP1P2M = dko(DataKsiegowaDefP1,+2,"m"); //Data księgowa plus jeden dzień plus jeden miesiąc
        DataKsiegowaDefP1P1R = dko(DataKsiegowaDefP1,+1,"y");
        DataKsiegowaDefP1R = dko(DataKsiegowaDef, +1, "y");
        DataKsiegowaDefP1P1MP1R = dko(DataKsiegowaDefP1P1M, +1, "y");
        
        api.PIK_ZeZmiennej(idSesji, "SS.Baza", out);
        FBaza = out[0];
        api.PIK_ZeZmiennej(idSesji, "SS.Katalog", out);
        FKatalog = out[0];
        api.PIK_ZeZmiennej(idSesji, "SS.WersjaPro", out);
        FWersjaDef = (out[0]).substring(9);
        api.PIK_ZeZmiennej(idSesji, "SS.Wersja", out);
        FWersjaExe = out[0];
        
        wynik = api.PIK_UsunWszystkieZmienne(idSesji);
        System.out.println("PIK_UsunWszystkieZmienne : " + wynik);
        
        sendTeamsMessage("Uruchomiono testy automatyczne defAPI:<br>  - kto - <b>" + System.getProperty("user.name") + "</b><br>  - baza - <b>" + FBaza + "</b><br>  - katalog klienta - <b>" + FKatalog + "</b><br>  - wersja definicji - <b>" + FWersjaDef + "</b><br>  - wersja exe - <b>" + FWersjaExe + "</b><br><br> Proszę o:<br>  - nie wyłączanie serwera CORBA <br>  - nie zamykanie dni na bazie.<br><br>Dziękuję.");
        
        separator();
    }
    
    void zakonczPolaczenie() throws IOException
    {
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        int wynik = api.PIK_ZamknijSesje(idSesji);
        System.out.println();
        System.out.println("PIK_ZamknijSesje: " + wynik);
        zapiszZmienna("IloscFkcji", "" + fNr);
        sendTeamsMessage("Testy automatyczne defAPI na bazie " + FBaza + " zakończone.<br>Ilość błędów - " + fnErr);
    }
    
    void wyswietlBlad(int x) throws IOException
    {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String blad  = api.PIK_DajOpisBledu(idSesji);
        int nr_bledu = api.PIK_DajNrBledu(idSesji);
        System.out.println("PIK_DajNrBledu: " + nr_bledu);
        System.out.println("PIK_DajOpisBledu: " + blad);
        if(x != 0){
            System.out.println("Naciśnij enter aby kontynuować.");
            stdin.readLine();
            fnErr++;
        }
    }
    
    void PIK_AutoryzacjaOnLine1() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        //Zmienne in
        String NRB = "", tmp ="";
        int NrAutoryzacji = 0, wynik=0;
        
        //Zmienne out
        String[] wartosc = new String[200];
        String[] NR_AUTORYZACJI = new String[200];
        String[] KOD_ODPOWIEDZI = new String[200];
        String[] OPIS_ODPOWIEDZI = new String[200];
        String[] WOLNE_SRODKI = new String[200];
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        //Wczytanie zmiennych
        NRB = wczytajZmienna("oNRB");
        tmp = wczytajZmienna("iNrAutoryzacjiOnLine");
        NrAutoryzacji = Integer.parseInt(tmp);
        
        wynik = api.PIK_DoZmiennej (idSesji, "AO.ID_TRANSAKCJI", defAPI.Z_RACH, NrAutoryzacji + "");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.TYP_TRANSAKCJI", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.KWOTA", defAPI.Z_KWOTA, "100");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.WALUTA", defAPI.Z_RACH, "985");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.NR_KARTY", defAPI.Z_BUFOR, "6765041211447012");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.DATA_CZAS_GMT", defAPI.Z_BUFOR, "0330133025");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.DATA_CZAS_LOK", defAPI.Z_BUFOR, "0401123025");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.ID_ACQUIRERA", defAPI.Z_BUFOR, "123456789");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.ID_TERMINALA", defAPI.Z_BUFOR, "aaab0666");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.ID_MERCHANTA", defAPI.Z_BUFOR, "ABC71199");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.NAZWA_MERCHANTA", defAPI.Z_BUFOR, "Regres defApi");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.MIASTO_MERCHANTA", defAPI.Z_BUFOR, "Rzeszow");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.STAN_MERCHANTA", defAPI.Z_BUFOR, "POD");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.KRAJ_MERCHANTA", defAPI.Z_BUFOR, "PL");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.NRB", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "AO.NR_AUTORYZACJI", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.KOD_ODPOWIEDZI", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.OPIS_ODPOWIEDZI", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.WOLNE_SRODKI", defAPI.Z_BUFOR, "");
        
        // Wywolanie
        wynik = api.PIK_AutoryzacjaOnline(idSesji, defAPI.AO_MAKE_AUTH);
        System.out.println("PIK_AutoryzacjaOnline  : " + wynik);
        
        if (wynik == 0){
            System.out.println("PIK_AutoryzacjaOnLine: Błąd");
            wyswietlBlad(1);
        }
        else {
            System.out.println("PIK_AutoryzacjaOnLine: OK");
            
            wynik = api.PIK_ZeZmiennej (idSesji, "AO.NR_AUTORYZACJI", NR_AUTORYZACJI);
            System.out.println("AO.NR_AUTORYZACJI    : " + NR_AUTORYZACJI[0]);
            System.out.println("PIK_ZeZmiennej AO.NR_AUTORYZACJI" + wynik);
            wynik = api.PIK_ZeZmiennej (idSesji, "AO.KOD_ODPOWIEDZI", KOD_ODPOWIEDZI);
            System.out.println("AO.KOD_ODPOWIEDZI    : " + KOD_ODPOWIEDZI[0]);
            System.out.println("PIK_ZeZmiennej AO.KOD_ODPOWIEDZI" + wynik);
            wynik = api.PIK_ZeZmiennej (idSesji, "AO.OPIS_ODPOWIEDZI", OPIS_ODPOWIEDZI);
            System.out.println("AO.OPIS_ODPOWIEDZI    : " + OPIS_ODPOWIEDZI[0]);
            System.out.println("PIK_ZeZmiennej AO.OPIS_ODPOWIEDZI" + wynik);
            wynik = api.PIK_ZeZmiennej (idSesji, "AO.WOLNE_SRODKI", WOLNE_SRODKI);
            System.out.println("AO.WOLNE_SRODKI    : " + Float.valueOf(WOLNE_SRODKI[0]).floatValue() / 100);
            System.out.println("PIK_ZeZmiennej AO.WOLNE_SRODKI" + wynik);
        }
        zapiszZmienna("oIdTransakcji", NrAutoryzacji + "");
        NrAutoryzacji++;
        zapiszZmienna("iNrAutoryzacjiOnLine", NrAutoryzacji + "");
        separator();

    }
    
    void PIK_AutoryzacjaOnLine1X() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        //Zmienne in
        String NRB = "", tmp ="";
        int NrAutoryzacji = 0, wynik=0;
        
        //Zmienne out
        String[] wartosc = new String[200];
        String[] NR_AUTORYZACJI = new String[200];
        String[] KOD_ODPOWIEDZI = new String[200];
        String[] OPIS_ODPOWIEDZI = new String[200];
        String[] WOLNE_SRODKI = new String[200];
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        //Wczytanie zmiennych
        NRB = wczytajZmienna("oNRB");
        tmp = wczytajZmienna("iNrAutoryzacjiOnLine");
        NrAutoryzacji = Integer.parseInt(tmp);
        
        wynik = api.PIK_DoZmiennej (idSesji, "AO.ID_TRANSAKCJI", defAPI.Z_RACH, NrAutoryzacji + "");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.TYP_TRANSAKCJI", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.KWOTA", defAPI.Z_KWOTA, "100000000000");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.WALUTA", defAPI.Z_RACH, "985");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.NR_KARTY", defAPI.Z_BUFOR, "6765041211447012");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.DATA_CZAS_GMT", defAPI.Z_BUFOR, "0330133025");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.DATA_CZAS_LOK", defAPI.Z_BUFOR, "0401123025");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.ID_ACQUIRERA", defAPI.Z_BUFOR, "123456789");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.ID_TERMINALA", defAPI.Z_BUFOR, "aaab0666");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.ID_MERCHANTA", defAPI.Z_BUFOR, "ABC71199");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.NAZWA_MERCHANTA", defAPI.Z_BUFOR, "Regres defApi");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.MIASTO_MERCHANTA", defAPI.Z_BUFOR, "Rzeszow");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.STAN_MERCHANTA", defAPI.Z_BUFOR, "POD");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.KRAJ_MERCHANTA", defAPI.Z_BUFOR, "PL");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.NRB", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "AO.NR_AUTORYZACJI", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.KOD_ODPOWIEDZI", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.OPIS_ODPOWIEDZI", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "AO.WOLNE_SRODKI", defAPI.Z_BUFOR, "");
        
        // Wywolanie
        wynik = api.PIK_AutoryzacjaOnline(idSesji, defAPI.AO_MAKE_AUTH);
        System.out.println("PIK_AutoryzacjaOnline  : " + wynik);
        
        if (wynik == 0){
            System.out.println("PIK_AutoryzacjaOnLine: Błąd");
            wyswietlBlad(1);
        }
        else {
            System.out.println("PIK_AutoryzacjaOnLine: OK");
            
            wynik = api.PIK_ZeZmiennej (idSesji, "AO.NR_AUTORYZACJI", NR_AUTORYZACJI);
            System.out.println("AO.NR_AUTORYZACJI    : " + NR_AUTORYZACJI[0]);
            System.out.println("PIK_ZeZmiennej AO.NR_AUTORYZACJI" + wynik);
            wynik = api.PIK_ZeZmiennej (idSesji, "AO.KOD_ODPOWIEDZI", KOD_ODPOWIEDZI);
            System.out.println("AO.KOD_ODPOWIEDZI    : " + KOD_ODPOWIEDZI[0]);
            System.out.println("PIK_ZeZmiennej AO.KOD_ODPOWIEDZI" + wynik);
            wynik = api.PIK_ZeZmiennej (idSesji, "AO.OPIS_ODPOWIEDZI", OPIS_ODPOWIEDZI);
            System.out.println("AO.OPIS_ODPOWIEDZI    : " + OPIS_ODPOWIEDZI[0]);
            System.out.println("PIK_ZeZmiennej AO.OPIS_ODPOWIEDZI" + wynik);
            wynik = api.PIK_ZeZmiennej (idSesji, "AO.WOLNE_SRODKI", WOLNE_SRODKI);
            System.out.println("AO.WOLNE_SRODKI    : " + Float.valueOf(WOLNE_SRODKI[0]).floatValue() / 100);
            System.out.println("PIK_ZeZmiennej AO.WOLNE_SRODKI" + wynik);
        }
        zapiszZmienna("oIdTransakcji", NrAutoryzacji + "");
        NrAutoryzacji++;
        zapiszZmienna("iNrAutoryzacjiOnLine", NrAutoryzacji + "");
        separator();

    }
    
    void PIK_DajListeDekretow() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String modulo = "", konto = "", uwaga = "", kontoPom = "", dataOd = "", 
                dataDo = "", nrb = "", nrDekrOd = "", ileMaks = "", storno = "";
        String[] saldo1 = new String[200];
        String[] saldo2 = new String[200];
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        //Dane wejściowe
        nrb = wczytajZmienna("oNRB");
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "NRB", defAPI.Z_BUFOR, nrb);
        wynik = api.PIK_DoZmiennej(idSesji, "ListaProwizji", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "UnikalnyIdentyfikator", defAPI.Z_BUFOR, "");
        //OPER MPP
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KWOTA", defAPI.Z_RACH,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_NR_RACH_VAT", defAPI.Z_RACH,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KWOTA_VAT", defAPI.Z_KWOTA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KWOTA_VAT2", defAPI.Z_KWOTA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_IDC", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_INV", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_IDP", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_TXT", defAPI.Z_BUFOR,"");
        //ELIXIR MPP
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.KwotaVAT", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.IDC", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.INV", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.IDP", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.TXT", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.KodDokumentu", defAPI.Z_BUFOR,"");
        
        wynik = api.PIK_DajListeDekretow(idSesji, modulo, konto, uwaga, kontoPom, dataOd, dataDo, saldo1, saldo2);
        System.out.println("PIK_DajListeDekretow  : " + wynik);

        if(wynik == WYNIK_OK)
        {
            System.out.println("wynik == WYNIK_OK");

            System.out.println("saldo1 : " + saldo1[0]);
            double val = Double.parseDouble(saldo2[0]);
            System.out.println("saldo2 : " + (-val));

            System.out.println("PIK_LiczbaRekordow  : " +  api.PIK_LiczbaRekordow(idSesji));

            String[] wartosc = new String[200];

            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK)
            {
                int i = 0;
                do
                {
                    i += 1;
                    System.out.println(i + " : ");
                    
                    wynik = api.PIK_ZeZmiennej(idSesji, "UnikalnyIdentyfikator", wartosc);
                    System.out.println("\tUnikalny Identyfikator: " + wartosc[0] );
                    System.out.println("PIK_ZeZmiennej UnikalnyIdentyfikator: " + wynik);
                    
                    wynik = api.PIK_ZeZmiennej(idSesji, "ListaProwizji", wartosc);
                    System.out.println("\tLista prowizji: " + wartosc[0] );
                    System.out.println("PIK_ZeZmiennej ListaProwizji: " + wynik);
                    
                    //OPER MPP
                    wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KWOTA", wartosc);
                    System.out.println("OPER_KWOTA:\t" + wartosc[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "OPER_NR_RACH_VAT", wartosc);
                    System.out.println("OPER_NR_RACH_VAT:\t" + wartosc[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KWOTA_VAT", wartosc);
                    System.out.println("OPER_KWOTA_VAT:\t" + wartosc[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KWOTA_VAT2", wartosc);
                    System.out.println("OPER_KWOTA_VAT2:\t" + wartosc[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "OPER_IDC", wartosc);
                    System.out.println("OPER_IDC:\t" + wartosc[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "OPER_INV", wartosc);
                    System.out.println("OPER_INV:\t" + wartosc[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "OPER_IDP", wartosc);
                    System.out.println("OPER_IDP:\t" + wartosc[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "OPER_TXT", wartosc);
                    System.out.println("OPER_TXT:\t" + wartosc[0]);
                    //ELIXIR MPP
                    wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.KwotaVAT", wartosc);
                    System.out.println("ELIXIR.KwotaVAT:\t" + wartosc[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.IDC", wartosc);
                    System.out.println("ELIXIR.IDC:\t" + wartosc[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.INV", wartosc);
                    System.out.println("ELIXIR.INV:\t" + wartosc[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.IDP", wartosc);
                    System.out.println("ELIXIR.IDP:\t" + wartosc[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.TXT", wartosc);
                    System.out.println("ELIXIR.TXT:\t" + wartosc[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.KodDokumentu", wartosc);
                    System.out.println("ELIXIR.KodDokumentu:\t" + wartosc[0]);
                } while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }
        else
        {
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(1);
        }
        separator();
    }    
    
    void PIK_Klient3O() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String nazwa = "", typFormatki = "", adrKodPocztowy = "", adrMiejscowosc = "", 
                adrUlica = "", typKlienta = "", nazwisko = "", imie_1 = "", pesel = "";
        
        String[] ID = new String[100];
        String[] Mod = new String[100];
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        nazwa = "OsPryw_";
        typFormatki = "2";
        adrKodPocztowy ="11-111";
        adrMiejscowosc = "Miejscowo\u015B\u0107 KL";
        adrUlica = "Ulica KL";
        typKlienta = "15";
        nazwisko = "KL_Nazwisko ";
        imie_1 = "KL_Imie_1";
        
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Nazwa", defAPI.Z_BUFOR , nazwa);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.TypFormatki", defAPI.Z_BUFOR , typFormatki);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Adr_KodPocztowy", defAPI.Z_BUFOR , adrKodPocztowy);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Adr_Miejscowość", defAPI.Z_BUFOR , adrMiejscowosc);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Adr_Ulica", defAPI.Z_BUFOR , adrUlica);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.TypKlienta", defAPI.Z_BUFOR , typKlienta);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Nazwisko", defAPI.Z_BUFOR , nazwisko);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Imię_1", defAPI.Z_BUFOR , imie_1);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.NrKlienta", defAPI.Z_BUFOR , "");
        
        //Wywołanie
        wynik = api.PIK_Klient(idSesji, defAPI.KL_A_DODANIE);
        System.out.println("PIK_Klient  : " + wynik);
        
        if(wynik == WYNIK_OK){
            System.out.println("wynik == WYNIK_OK");
            api.PIK_ZeZmiennej(idSesji, "Klnt1.ID", ID);
            System.out.println("\tKlnt1.ID: " + ID[0] );
            zapiszZmienna("oIdKl", ID[0]);
            api.PIK_ZeZmiennej(idSesji, "Klnt1.NrKlienta", Mod);
            System.out.println("\tKlnt1.NrKlienta: " + Mod[0] );
            zapiszZmienna("oModKl", Mod[0]);
        }
        else{
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(1);
        }
        separator();

    }
    
    void PIK_Klient3F() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String nazwa = "", typFormatki = "", adrKodPocztowy = "", adrMiejscowosc = "", 
                adrUlica = "", typKlienta = "", nazwaKr = "", nazwaPe = "", pesel = "";
        
        String[] ID = new String[100];
        String[] Mod = new String[100];
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        nazwa = "Firma_";
        typFormatki = "3";
        adrKodPocztowy ="22-222";
        adrMiejscowosc = "Miejscowo\u015B\u0107 FIR";
        adrUlica = "Ulica FIR";
        typKlienta = "16";
        nazwaKr = "KL_Nazwisko ";
        nazwaPe = "KL_Imie_1";
        
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Nazwa", defAPI.Z_BUFOR , nazwa);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.TypFormatki", defAPI.Z_BUFOR , typFormatki);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Adr_KodPocztowy", defAPI.Z_BUFOR , adrKodPocztowy);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Adr_Miejscowość", defAPI.Z_BUFOR , adrMiejscowosc);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Adr_Ulica", defAPI.Z_BUFOR , adrUlica);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.TypKlienta", defAPI.Z_BUFOR , typKlienta);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.NazwaKrótka", defAPI.Z_BUFOR , nazwaKr);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.NazwaPełna", defAPI.Z_BUFOR , nazwaPe);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.NrKlienta", defAPI.Z_BUFOR , "");
        
        //Wywołanie
        wynik = api.PIK_Klient(idSesji, defAPI.KL_A_DODANIE);
        System.out.println("PIK_Klient  : " + wynik);
        
        if(wynik == WYNIK_OK){
            System.out.println("wynik == WYNIK_OK");
            api.PIK_ZeZmiennej(idSesji, "Klnt1.ID", ID);
            System.out.println("\tKlnt1.ID: " + ID[0] );
            zapiszZmienna("oIdKlF", ID[0]);
            api.PIK_ZeZmiennej(idSesji, "Klnt1.NrKlienta", Mod);
            System.out.println("\tKlnt1.NrKlienta: " + Mod[0] );
            zapiszZmienna("oModKlF", Mod[0]);
        }
        else{
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(1);
        }
        zapiszZmienna("oNRBVat", "");
        separator();

    }
    
    void PIK_Klient4() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String ID = "", nrKol = "";
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        ID = wczytajZmienna("oIdKl");
        nrKol = wczytajZmienna("iNrKolKl");
        
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.ID", defAPI.Z_BUFOR , ID);
        
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Nazwa", defAPI.Z_BUFOR , "OsPrywZm_" + nrKol);
        
        wynik = api.PIK_Klient(idSesji, defAPI.KL_A_MODYFIKACJA);
        System.out.println("PIK_Klient  : " + wynik);
        
        if(wynik == WYNIK_OK)
        {
            System.out.println("wynik == WYNIK_OK");
        }
        else
        {
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(1);

        }
        nrKol = Integer.toString(Integer.parseInt(nrKol) + 1);
        zapiszZmienna("iNrKolKl", nrKol);
        separator();
    }
    
    void PIK_WykonajZlecenie2() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String currDate = "", NRB = "", BN = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        currDate = dateFormat.format(date);
        NRB = wczytajZmienna("oNRB");
        BN = NRB.substring(2,10);

        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypKomunikatu", defAPI.Z_RACH, "111");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Status", defAPI.Z_RACH, "5.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Kwota", defAPI.Z_KWOTA, "123.45");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataKomunikatu", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataRealizacji", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankNadawca", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankAdresat", defAPI.Z_RACH, "13201537");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KorespondentNad", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.AdresatFinalny", defAPI.Z_RACH, "13201537");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekNadawcy", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekAdresata", defAPI.Z_BUFOR, "62132015370104005230000004");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaNadawcy", defAPI.Z_MEMO, "PIK WZ");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaAdresata", defAPI.Z_MEMO, "KLIENT testowy");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KodDokumentu", defAPI.Z_MEMO, "51");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Inf.Międzybank.", defAPI.Z_MEMO, "Pozycja nr 10\nSZ99714865\nT92011.5265658\nTTN\n");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDKomunikatu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypZlecenia", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Strumień", defAPI.Z_BUFOR, "20"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerJednostki", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypObsługi", defAPI.Z_BUFOR, "1.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypDrogi", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.OpisDodatkowy", defAPI.Z_MEMO, "Test Corba");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR, "WWW"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataDokumentu", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerDokumentu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Rozliczenie", defAPI.Z_RACH, "0");
        
        wynik = api.PIK_WykonajZlecenie(idSesji, defAPI.ELX_RK_UZNANIE, "WWW" , "0");
        
        System.out.println("PIK_WykonajZlecenie  : " + wynik);
        if (wynik == 0){
            System.out.println("PIK_Wykonaj zlecenie  : " + wynik);
            wyswietlBlad(1);
        }
        separator();
        try{
            Thread.sleep(ShortSleep);
        }catch (InterruptedException e){
        }
    }
    
    void PIK_WpiszKomunikat1_ZUS() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String currDate = "", NRB = "", BN = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        currDate = dateFormat.format(date);
        NRB = wczytajZmienna("oNRB");
        BN = NRB.substring(2,10);

        String NRB_ZUS = NRB_ZUS_NIP();
        
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypKomunikatu", defAPI.Z_RACH, "111");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Status", defAPI.Z_RACH, "5.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Kwota", defAPI.Z_KWOTA, "123.45");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataKomunikatu", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataRealizacji", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankNadawca", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankAdresat", defAPI.Z_RACH, NRB_ZUS.substring(2,10));
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KorespondentNad", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.AdresatFinalny", defAPI.Z_RACH, NRB_ZUS.substring(2,10));
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekNadawcy", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekAdresata", defAPI.Z_BUFOR, NRB_ZUS);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaNadawcy", defAPI.Z_MEMO, "Przelew ZUS");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaAdresata", defAPI.Z_MEMO, "KLIENT testowy");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KodDokumentu", defAPI.Z_MEMO, "51");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Inf.Międzybank.", defAPI.Z_MEMO, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDKomunikatu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypZlecenia", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Strumień", defAPI.Z_BUFOR, "20"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerJednostki", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypObsługi", defAPI.Z_BUFOR, "1.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypDrogi", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.OpisDodatkowy", defAPI.Z_MEMO, "Test Corba");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR, "WWW"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataDokumentu", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerDokumentu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Rozliczenie", defAPI.Z_RACH, "0");
        
        wynik = api.PIK_WpiszKomunikat(idSesji, defAPI.ELX_RK_UZNANIE, "WWW");
        
        System.out.println("PIK_WpiszKomunikat  : " + wynik);
        if (wynik == 0){
            System.out.println("PIK_WpiszKomunikat  : " + wynik);
            wyswietlBlad(1);
        }
        separator();
        try{
            Thread.sleep(ShortSleep);
        }catch (InterruptedException e){
        }
    }
    
    void PIK_WpiszKomunikat1() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String rachNadawcy = "", dataKomunikatu = "";

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();

        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);

        rachNadawcy = wczytajZmienna("oNRB");

        dataKomunikatu = dateFormat.format(date);
        
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Strumien, defAPI.Z_KWOTA , "20");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypZlecenia, defAPI.Z_RACH , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KodDokumentu, defAPI.Z_BUFOR , "PR");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KorespondentNad, defAPI.Z_RACH , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekNadawcy, defAPI.Z_BUFOR , rachNadawcy);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaNadawcy, defAPI.Z_MEMO , "PIK WK");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_AdresatFinalny, defAPI.Z_RACH , "21300004");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekAdresata, defAPI.Z_BUFOR , "63213000042001019529770001");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaAdresata, defAPI.Z_MEMO , "KLIENT testowy");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Kwota, defAPI.Z_KWOTA , "123.89");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Waluta, defAPI.Z_BUFOR , "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_DataKomunikatu, defAPI.Z_DATA , dataKomunikatu);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_OpisDodatkowy, defAPI.Z_MEMO , "Przelew \u015Brodk\u00F3w 1\nPrzelew \u015Brodk\u00F3w 2\nPrzelew \u015Brodk\u00F3w 3");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Rozliczenie, defAPI.Z_RACH , "0");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Inf_Miedzybank, defAPI.Z_BUFOR , "1");
        //PSD
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzyPSD", defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.DataPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzasPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.Kurs", defAPI.Z_BUFOR , "0.0");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.ListaOplat", defAPI.Z_BUFOR , "<Dysp.Oplaty></Dysp.Oplaty>");
        
        wynik = api.PIK_WpiszKomunikat(idSesji, defAPI.ELX_RK_UZNANIE, "WWW");
        System.out.println("PIK_WpiszKomunikat  : " + wynik);            

        String[] wartosc = new String[1];
        
        if(wynik == WYNIK_OK)            
        {
            api.PIK_ZeZmiennej(idSesji, defAPI.ELIXIR_ID, wartosc);
            System.out.println("\tELIXIR_ID: " + wartosc[0] );
        }
        else
        {
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(1);            
        }
        separator();
        try{
            Thread.sleep(LongSleep);
        }catch (InterruptedException e){
        }
        zapiszZmienna("oElixirID", wartosc[0]);
    }
    
    void PIK_PobierzKomunikat() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        String ID_ELX = "";

        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);

        ID_ELX = wczytajZmienna("oElixirID");
        
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.ID",defAPI.Z_BUFOR, ID_ELX);
        
        wynik = api.PIK_PobierzKomunikat(idSesji);
        
        System.out.println("PIK_PobierzKomunikat  : " + wynik);
        
        if (wynik == 0){
            wyswietlBlad(1);
        }
        else{
            String[] test = new String[200];
                    
            api.PIK_ZeZmiennej(idSesji, "ELIXIR.R-ekNadawcy" , test);
            System.out.println("Rachunek nadawcy:\t" + test[0]);
            api.PIK_ZeZmiennej(idSesji, "ELIXIR.R-ekAdresata" , test);
            System.out.println("Rachunek adresata:\t" + test[0]);
            api.PIK_ZeZmiennej(idSesji, "ELIXIR.NazwaAdresata" , test);
            System.out.println("Nazwa adresata:\t" + test[0]);
            api.PIK_ZeZmiennej(idSesji, "ELIXIR.OpisDodatkowy" , test);
            System.out.println("Tytuł przelewu:\t" + test[0]);
            api.PIK_ZeZmiennej(idSesji, "ELIXIR.Kwota" , test);
            System.out.println("Kwota przelewu:\t" + test[0]);
            api.PIK_ZeZmiennej(idSesji, "ELIXIR.OpisReklamacji" , test);
            System.out.println("Przyczyna braku realizacji komunikatu: " + test[0]);
            api.PIK_ZeZmiennej(idSesji, "ELIXIR.Status" , test);
            System.out.println("Status komunikatu: " + test[0]);
            
            wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.KwotaVAT", test);
            System.out.println("ELIXIR.KwotaVAT:\t" + test[0]);
            wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.IDC", test);
            System.out.println("ELIXIR.IDC:\t" + test[0]);
            wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.INV", test);
            System.out.println("ELIXIR.INV:\t" + test[0]);
            wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.IDP", test);
            System.out.println("ELIXIR.IDP:\t" + test[0]);
            wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.TXT", test);
            System.out.println("ELIXIR.TXT:\t" + test[0]);
            wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.KodDokumentu", test);
            System.out.println("ELIXIR.KodDokumentu:\t" + test[0]);
            api.PIK_ZeZmiennej(idSesji, "ELIXIR.NrDokumentu" , test);
            System.out.println("ELIXIR.NrDokumentu: " + test[0]);
            zapiszZmienna("oNrDok", test[0]);
            
        }
        separator();
    }
    
    void PIK_UmowaRB1() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String modulo = "";
        
        modulo = wczytajZmienna("oModKl");
        
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Modulo",defAPI.Z_BUFOR, modulo);
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.RodzajRachunku", defAPI.Z_BUFOR, "ROR");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Jednostka", defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Data", defAPI.Z_BUFOR, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "RB.DataWaluty", defAPI.Z_BUFOR, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Oprocentowanie", defAPI.Z_BUFOR, "INDYWIDUALNE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.ZrodloProwizji", defAPI.Z_BUFOR, "Z RACHUNKU TRANSAKCJI");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.TrybRozliczOdsMA", defAPI.Z_BUFOR, "KAPITALIZACJA");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.OkresRozlOdsWN", defAPI.Z_BUFOR, "WG PARAM.");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.HomeBanking", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.TeleInfo", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.RachunekSMS", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.LokKartaDebet", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.RachunekAktKP", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.TypWyciagu", defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.WyciagOkres", defAPI.Z_BUFOR, "PO OPERACJI");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.WyciagWysylac", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.RachunekIC", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.LokataOvernight", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Wpisał", defAPI.Z_BUFOR, "16702"); //SANOK
        //wynik = api.PIK_DoZmiennej(idSesji, "RB.Wpisał", defAPI.Z_BUFOR, "65536"); //VW
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Status", defAPI.Z_BUFOR, "4");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.PodTyp", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "defapi.Id", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "defapi.Modulo", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "defapi.IdKlienta", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "defapi.StatusAkcji", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "defapi.NRB", defAPI.Z_BUFOR, "");
        
        String [] idUmowy = new String[200];
        idUmowy[0] = "";
        wynik = api.PIK_UmowaRB(idSesji, defAPI.RB_A_ZALOZENIE, idUmowy);
        System.out.println("PIK_UmowaRB  : " + wynik);
         
        if (wynik == 0){
            wyswietlBlad(1);
        }
        else{
            String [] Modulo = new String[200];
            String [] StatusAkcji = new String[200];
            String [] dKlienta = new String[200];
            String [] NRB = new String[200];
        
            wynik = api.PIK_ZeZmiennej (idSesji, "defapi.Modulo", Modulo);
            System.out.println("Modulo: " + Modulo[0]);
            wynik = api.PIK_ZeZmiennej (idSesji, "defapi.StatusAkcji", StatusAkcji);
            System.out.println("Status Akcji: " + StatusAkcji[0]);
            wynik = api.PIK_ZeZmiennej (idSesji, "defapi.IdKlienta", dKlienta);
            System.out.println("ID Klienta: " + dKlienta[0]);
            wynik = api.PIK_ZeZmiennej (idSesji, "defapi.NRB", NRB);
            System.out.println("NRB: " + NRB[0]);
            zapiszZmienna("oNRB", NRB[0]);
        }
        separator();
    }
    
    void PIK_UmowaRB1_MPP() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String modulo = "", NRBVat = "";
        
        modulo = wczytajZmienna("oModKlF");
        NRBSrodki=wczytajZmienna("oNRB");
        NRBVat=wczytajZmienna("oNRBVat");
        
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Modulo",defAPI.Z_BUFOR, modulo);
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.RodzajRachunku", defAPI.Z_BUFOR, "RBS");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Jednostka", defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Data", defAPI.Z_BUFOR, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "RB.DataWaluty", defAPI.Z_BUFOR, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Oprocentowanie", defAPI.Z_BUFOR, "INDYWIDUALNE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.ZrodloProwizji", defAPI.Z_BUFOR, "Z RACHUNKU TRANSAKCJI");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.TrybRozliczOdsMA", defAPI.Z_BUFOR, "KAPITALIZACJA");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.OkresRozlOdsWN", defAPI.Z_BUFOR, "WG PARAM.");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.HomeBanking", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.TeleInfo", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.RachunekSMS", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.LokKartaDebet", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.RachunekAktKP", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.TypWyciagu", defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.WyciagOkres", defAPI.Z_BUFOR, "PO OPERACJI");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.WyciagWysylac", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.RachunekIC", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.LokataOvernight", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Wpisał", defAPI.Z_BUFOR, "16702"); //SANOK
        //wynik = api.PIK_DoZmiennej(idSesji, "RB.Wpisał", defAPI.Z_BUFOR, "65536"); //VW
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Status", defAPI.Z_BUFOR, "4");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.PodTyp", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "defapi.Id", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "defapi.Modulo", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "defapi.IdKlienta", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "defapi.StatusAkcji", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "defapi.NRB", defAPI.Z_BUFOR, "");
        //DaneMPP - automatyczne założenie RB + VAT
        wynik = api.PIK_DoZmiennej(idSesji, "RB.ObslugaMPP", defAPI.Z_BUFOR, "Tak");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.RachunekVAT", defAPI.Z_BUFOR, NRBVat);
        
        String [] idUmowy = new String[200];
        idUmowy[0] = "";
        wynik = api.PIK_UmowaRB(idSesji, defAPI.RB_A_ZALOZENIE, idUmowy);
        System.out.println("PIK_UmowaRB  : " + wynik);
         
        if (wynik == 0){
            wyswietlBlad(1);
        }
        else{
            String [] Modulo = new String[200];
            String [] StatusAkcji = new String[200];
            String [] dKlienta = new String[200];
            String [] NRB = new String[200];
        
            wynik = api.PIK_ZeZmiennej (idSesji, "defapi.Modulo", Modulo);
            System.out.println("Modulo: " + Modulo[0]);
            wynik = api.PIK_ZeZmiennej (idSesji, "defapi.StatusAkcji", StatusAkcji);
            System.out.println("Status Akcji: " + StatusAkcji[0]);
            wynik = api.PIK_ZeZmiennej (idSesji, "defapi.IdKlienta", dKlienta);
            System.out.println("ID Klienta: " + dKlienta[0]);
            wynik = api.PIK_ZeZmiennej (idSesji, "defapi.NRB", NRB);
            System.out.println("NRB: " + NRB[0]);
            zapiszZmienna("oNRB", NRB[0]);
        }
        separator();
    }
    
    void PIK_UmowaRB13() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String NRB = "";
        
        NRB = wczytajZmienna("oNRB");
        
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Referencja",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.idUmowy",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.NRB",defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej(idSesji, "RB.RodzajLimitu",defAPI.Z_BUFOR, "Kredyt");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.DataUmowyLimitu",defAPI.Z_DATA, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "RB.Limit",defAPI.Z_KWOTA, "10000.00");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.DataPrzyznLimitu",defAPI.Z_DATA, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "RB.TypGeneracjiTerm",defAPI.Z_BUFOR, "A");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.IloscRatKapit",defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.OdstepRat_Liczn",defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.OdstepRat_Jedn",defAPI.Z_BUFOR, "M");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.DataPierwszejRaty",defAPI.Z_DATA, DataKsiegowaDefP1);
        wynik = api.PIK_DoZmiennej(idSesji, "RB.JakaRataRoznicowa",defAPI.Z_BUFOR, "O");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.ZaokrRatKapit",defAPI.Z_KWOTA, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "RB.KredytKonsumencki",defAPI.Z_BUFOR, "KK2");
        
        String [] idUmowy = new String[200];
        idUmowy[0] = "";
        wynik = api.PIK_UmowaRB(idSesji, defAPI.RB_A_ZALOZENIE_LIMITU, idUmowy);
        System.out.println("PIK_UmowaRB  : " + wynik);
        if (wynik == 0){
            wyswietlBlad(1);
        }
        else{

        }
        separator();
    }
    
    void PIK_UpowaznieniaPZ0() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String NRB = "", IDP = "";

        NRB = wczytajZmienna("oNRB");
        IDP = wczytajZmienna("oIdTransakcji");
        
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_NRBDluznika",defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_OkresOd",defAPI.Z_DATA, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_OkresDo",defAPI.Z_DATA, DataKsiegowaDefP1);
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_NazwaDluznika",defAPI.Z_MEMO, "Nazwa Dluznika");
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_NazwaWierzyciela",defAPI.Z_MEMO, "Nazwa Wierzyciela");
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_NIP",defAPI.Z_BUFOR, "1111111111");
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_IDP",defAPI.Z_BUFOR, IDP);
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_Wstrzymanie",defAPI.Z_RACH, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_DaneDoPralni",defAPI.Z_KWOTA, "0");
        
        wynik = api.PIK_UpowaznieniaPZ(idSesji, api.UPPZ_AKCJA_DODANIE);
        System.out.println("PIK_UpowaznieniaPZ : " + wynik);
        
        if(wynik == 0){
            wyswietlBlad(1);
        }
        else{
            
        }
        separator();
    }
    
    void PIK_UpowaznieniaPZ1() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String IDU = "";

        IDU = wczytajZmienna("oIdUpowaznienia");
        
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_ID",defAPI.Z_RACH, IDU);
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_NazwaDluznika",defAPI.Z_BUFOR, "Dłużnik Zmieniony");
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_NazwaWierzyciela",defAPI.Z_BUFOR, "Wierzyciel Zmieniony");
        
        wynik = api.PIK_UpowaznieniaPZ(idSesji, api.UPPZ_AKCJA_MODYFIKACJA);
        System.out.println("PIK_UpowaznieniaPZ : " + wynik);
        
        if(wynik == 0){
            wyswietlBlad(1);
        }
        else{
            
        }
        separator();
    }
    
    void PIK_UpowaznieniaPZ3() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        String NRB = "";
        NRB = wczytajZmienna("oNRB");
        
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_ID",defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_IDKlient",defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_NRBDluznika",defAPI.Z_BUFOR, NRB);
        
        wynik = api.PIK_UpowaznieniaPZ(idSesji, api.UPPZ_AKCJA_POBRANIE_LISTY);
        System.out.println("PIK_UpowaznieniaPZ : " + wynik);
        
        if(wynik == 0){
            wyswietlBlad(1);
        }
        else if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
            String[] tmp = new String[200];
            int i = 0;
            do{
                i += 1;
                System.out.println(i + " : ");
                
                wynik = api.PIK_ZeZmiennej(idSesji, "UPPZ_ID", tmp);
                System.out.println("ID Polecenia Zapłaty: " + tmp[0] );
                zapiszZmienna("oIdUpowaznienia", tmp[0]);
            } while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
        }
        separator();
        
    }
    
    void PIK_UpowaznieniaPZ2() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String IDU = "";

        IDU = wczytajZmienna("oIdUpowaznienia");
        
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_ID",defAPI.Z_RACH, IDU);
        wynik = api.PIK_DoZmiennej(idSesji, "UPPZ_Data",defAPI.Z_DATA, DataKsiegowaDefM1);
        
        wynik = api.PIK_UpowaznieniaPZ(idSesji, api.UPPZ_AKCJA_USUNIECIE);
        System.out.println("PIK_UpowaznieniaPZ : " + wynik);
        
        if(wynik == 0){
            wyswietlBlad(1);
        }
        else{
            
        }
        separator();
    }
    
    void PIK_WykonajProcedure_PobierzLTK() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "KRLTK.Jednostka",defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "KRLTK.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "Pobierz_LTK");
        System.out.println("PIK_WykonajProcedure Pobierz_LTK: " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "KRLTK.Lista", lista);
            System.out.println("KRLTK.Lista : " + lista[0]);
        }
        else{
            wyswietlBlad(1);
        }
        
        separator();        
    }
    
    void PIK_WykonajProcedure_TestRachunku1() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "KP.KodBledu",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "KP.TypAkcji",defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "KP.Modulo",defAPI.Z_BUFOR, "84002424");
        wynik = api.PIK_DoZmiennej(idSesji, "KP.Konto",defAPI.Z_BUFOR, "27414-101");
        wynik = api.PIK_DoZmiennej(idSesji, "KP.Uwaga",defAPI.Z_BUFOR, "01");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "TestRachunku");
        System.out.println("PIK_WykonajProcedure TestRachunku: " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "KP.KodBledu", lista);
            System.out.println("KP.KodBledu : " + lista[0]);
        }
        else{
            wyswietlBlad(1);
        }
        
        separator();  
    }
    
    void PIK_WykonajProcedure_PobierzListeProduktow1() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "Produkt.KodBledu",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Produkt.OpisBledu",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Produkt.Grupa",defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "Produkt.Typ",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Produkt.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "PobierzListeProduktow");
        System.out.println("PIK_WykonajProcedure PobierzListeProduktow: " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "Produkt.Lista", lista);
            System.out.println("Produkt.Lista : " + lista[0]);
            wynik = api.PIK_ZeZmiennej( idSesji, "Produkt.KodBledu", lista);
            System.out.println("Produkt.KodBledu : " + lista[0]);
            wynik = api.PIK_ZeZmiennej( idSesji, "Produkt.OpisBledu", lista);
            System.out.println("Produkt.OpisBledu : " + lista[0]);
        }
        else{
            wyswietlBlad(1);
            wynik = api.PIK_ZeZmiennej( idSesji, "Produkt.KodBledu", lista);
            System.out.println("Produkt.KodBledu : " + lista[0]);
            wynik = api.PIK_ZeZmiennej( idSesji, "Produkt.OpisBledu", lista);
            System.out.println("Produkt.OpisBledu : " + lista[0]);
        }
        separator();
    }
    
    void PIK_WykonajProcedure_PobierzListeProduktow2() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "Produkt.KodBledu",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Produkt.OpisBledu",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Produkt.Grupa",defAPI.Z_BUFOR, "2");
        wynik = api.PIK_DoZmiennej(idSesji, "Produkt.Typ",defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "Produkt.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "PobierzListeProduktow");
        System.out.println("PIK_WykonajProcedure PobierzListeProduktow: " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "Produkt.Lista", lista);
            System.out.println("Produkt.Lista : " + lista[0]);
            wynik = api.PIK_ZeZmiennej( idSesji, "Produkt.KodBledu", lista);
            System.out.println("Produkt.KodBledu : " + lista[0]);
            wynik = api.PIK_ZeZmiennej( idSesji, "Produkt.OpisBledu", lista);
            System.out.println("Produkt.OpisBledu : " + lista[0]);
        }
        else{
            wyswietlBlad(1);
            wynik = api.PIK_ZeZmiennej( idSesji, "Produkt.KodBledu", lista);
            System.out.println("Produkt.KodBledu : " + lista[0]);
            wynik = api.PIK_ZeZmiennej( idSesji, "Produkt.OpisBledu", lista);
            System.out.println("Produkt.OpisBledu : " + lista[0]);
        }
        separator();
    }
    
    void PIK_WykonajProcedure_PobierzListeOkresow() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "Okres.KodBledu",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Okres.OpisBledu",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Okres.TypProduktu",defAPI.Z_BUFOR, "");
    //Wpisać zgodnie z <PROD_KOD>  z wyniku procedury PobierzListeProduktow
        wynik = api.PIK_DoZmiennej(idSesji, "Okres.Produkt",defAPI.Z_BUFOR, "STOS");
        wynik = api.PIK_DoZmiennej(idSesji, "Okres.Jednostka",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Okres.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "PobierzListeOkresow");
        System.out.println("PIK_WykonajProcedure PobierzListeOkresow: " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "Okres.Lista", lista);
            System.out.println("Okres.Lista : " + lista[0]);
            wynik = api.PIK_ZeZmiennej( idSesji, "Okres.KodBledu", lista);
            System.out.println("Okres.KodBledu : " + lista[0]);
            wynik = api.PIK_ZeZmiennej( idSesji, "Okres.OpisBledu", lista);
            System.out.println("Okres.OpisBledu : " + lista[0]);
        }
        else{
            wyswietlBlad(1);
            wynik = api.PIK_ZeZmiennej( idSesji, "Okres.KodBledu", lista);
            System.out.println("Okres.KodBledu : " + lista[0]);
            wynik = api.PIK_ZeZmiennej( idSesji, "Okres.OpisBledu", lista);
            System.out.println("Okres.OpisBledu : " + lista[0]);
        }
        separator();
    }

    void PIK_WykonajProcedure_Pobierz_UBK() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik =0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "KRUBK.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "Pobierz_UBK");
        System.out.println("PIK_WykonajProcedure Pobierz_UBK: " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "KRUBK.Lista", lista);
            System.out.println("KRUBK.Lista : " + lista[0]);
        }
        else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_Zdarzenia() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "Zd.Grupa",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Zd.Numer",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "Zd.Limit",defAPI.Z_BUFOR, "3");
        wynik = api.PIK_DoZmiennej(idSesji, "Zd.Kod",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Zd.Typ",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Zd.ID",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Zd.Nazwa",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Zd.Data",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Zd.Czas",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Zd.Kto",defAPI.Z_BUFOR, "");
        
        wynik = api.PIK_Zdarzenia(idSesji, api.ZD_A_LISTA);
        System.out.println("PIK_Zdarzenia : " + wynik);
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("PIK_LiczbaRekordow : " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                String[] tmp = new String[1];
                int i = 0;
                do{
                    i += 1;
                    System.out.println(i + " : ");
                
                    wynik = api.PIK_ZeZmiennej(idSesji, "Zd.Numer", tmp);
                    System.out.println("Zd.Numer: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Zd.Kod", tmp);
                    System.out.println("Zd.Kod: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Zd.Typ", tmp);
                    System.out.println("Zd.Typ: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Zd.ID", tmp);
                    System.out.println("Zd.ID: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Zd.Nazwa", tmp);
                    System.out.println("Zd.Nazwa: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Zd.Data", tmp);
                    System.out.println("Zd.Data: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Zd.Czas", tmp);
                    System.out.println("Zd.Czas: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Zd.Kto", tmp);
                    System.out.println("Zd.Kto: " + tmp[0] );
                    
                } while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }
        else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_DajKursyWalut() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "KWAL_Tabela", defAPI.Z_BUFOR, "NBP");
        wynik = api.PIK_DoZmiennej(idSesji, "KWAL_Kraj", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "KWAL_Jednostka", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "KWAL_Symbol", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "KWAL_Kupno", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "KWAL_Sprzedaz", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "KWAL_KupnoDewiz", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "KWAL_SprzedazDewiz", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "KWAL_Sredni", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "KWAL_Tabela_DataOd", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej(idSesji, "KWAL_Tabela_CzasOd", defAPI.Z_DATA, "");
        
        wynik = api.PIK_DajKursyWalut(idSesji);
        System.out.println("PIK_DajKursyWalut : " + wynik);
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("PIK_LiczbaRekordow : " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                String[] tmp = new String[1];
                int i = 0;
                do{
                    i += 1;
                    System.out.println(i + " : ");
                
                    wynik = api.PIK_ZeZmiennej(idSesji, "KWAL_Kraj", tmp);
                    System.out.println("KWAL_Kraj: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "KWAL_Jednostka", tmp);
                    System.out.println("KWAL_Jednostka: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "KWAL_Symbol", tmp);
                    System.out.println("KWAL_Symbol: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "KWAL_Kupno", tmp);
                    System.out.println("KWAL_Kupno: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "KWAL_Sprzedaz", tmp);
                    System.out.println("KWAL_Sprzedaz: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "KWAL_KupnoDewiz", tmp);
                    System.out.println("KWAL_KupnoDewiz: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "KWAL_SprzedazDewiz", tmp);
                    System.out.println("KWAL_SprzedazDewiz: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "KWAL_Sredni", tmp);
                    System.out.println("KWAL_Sredni: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "KWAL_Tabela_DataOd", tmp);
                    System.out.println("KWAL_Tabela_DataOd: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "KWAL_Tabela_CzasOd", tmp);
                    System.out.println("KWAL_Tabela_CzasOd: " + tmp[0] );
                    
                } while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }
        else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_Kalendarz() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "KAL.WALUTA", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "KAL.NUMER", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej(idSesji, "KAL.DATA_OD", defAPI.Z_DATA, "2010.01.01");
        wynik = api.PIK_DoZmiennej(idSesji, "KAL.DATA", defAPI.Z_DATA, "");
        
        wynik = api.PIK_Kalendarz(idSesji, api.KALENDARZ_A_DAJ_DNI_WOLNE);
        System.out.println("PIK_Kalendarz : " + wynik);
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("PIK_LiczbaRekordow : " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                String[] tmp = new String[1];
                int i = 0;
                do{
                    i += 1;
                    System.out.println(i + " : ");
                
                    wynik = api.PIK_ZeZmiennej(idSesji, "KAL.DATA", tmp);
                    System.out.println("KAL.Data: " + tmp[0] );
                    
                } while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }
        else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_Uprawnienia3() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String [] upr = new String[1];
        upr[0] = wczytajZmienna("iUprawnienie");
        
        wynik = api.PIK_Uprawnienia(idSesji, api.UP_A_KONTROLA_UPRAWNIENIA, upr);
        
        System.out.println("PIK_Uprawnienia: " + wynik);
        if (wynik == WYNIK_OK){
            System.out.println("Operator posiada uprawnienie " + upr[0]);
        }
        else{
            wyswietlBlad(1);
        }
    }
    
    void PIK_Klient1() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String modulo = "";
        modulo = wczytajZmienna("oModKl");
        
        wynik = api.PIK_DoZmiennejNr(idSesji, api.PIK_GZ_KL, 95, modulo);
        wynik = api.PIK_DoZmiennejNr(idSesji, api.PIK_GZ_KL, 2, "");
        wynik = api.PIK_DoZmiennejNr(idSesji, api.PIK_GZ_KL, 4, "");
        wynik = api.PIK_DoZmiennejNr(idSesji, api.PIK_GZ_KL, 28, "");
        
        wynik = api.PIK_Klient(idSesji, api.KL_A_POBRANIE);
        System.out.println("PIK_Klient : " + wynik);
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("PIK_LiczbaRekordow : " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                String[] tmp = new String[1];
                int i = 0;
                do{
                    i += 1;
                    System.out.println(i + " : ");
                
                    wynik = api.PIK_ZeZmiennejNr(idSesji, api.PIK_GZ_KL, 2, tmp);
                    System.out.println("KL_Nazwa: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennejNr(idSesji, api.PIK_GZ_KL, 4, tmp);
                    System.out.println("KL_TypKlienta: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennejNr(idSesji, api.PIK_GZ_KL, 28, tmp);
                    System.out.println("KL_Kraj: " + tmp[0] );
                } while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_Klient2() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        java.util.Calendar tmpDK = new java.util.GregorianCalendar();
        tmpDK.add(Calendar.MONTH,-1);
        
        int wynik = 0;
        String cDate = "";
        
        cDate = "" + tmpDK.get(Calendar.YEAR) + ".";
        if((tmpDK.get(Calendar.MONTH)+1) < 10){
            cDate = cDate + "0" + (tmpDK.get(Calendar.MONTH)+1) + ".";
        }
        else{
            cDate = cDate + "" + (tmpDK.get(Calendar.MONTH)+1) + ".";
        }
        if(tmpDK.get(Calendar.DAY_OF_MONTH) < 10){
            cDate = cDate + "0" + tmpDK.get(Calendar.DAY_OF_MONTH);
        }
        else{
            cDate = cDate + "" + tmpDK.get(Calendar.DAY_OF_MONTH);
        }
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennejNr(idSesji, api.PIK_GZ_KL, 98, cDate);
        wynik = api.PIK_DoZmiennejNr(idSesji, api.PIK_GZ_KL, 1, "");
        wynik = api.PIK_DoZmiennejNr(idSesji, api.PIK_GZ_KL, 99, "");
        
        wynik = api.PIK_Klient(idSesji, api.KL_A_LISTA_ZMIAN);
        System.out.println("PIK_Klient : " + wynik);
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("PIK_LiczbaRekordow : " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                String[] tmp = new String[1];
                int i = 0;
                do{
                    i += 1;
                    System.out.println(i + " : ");
                
                    wynik = api.PIK_ZeZmiennejNr(idSesji, api.PIK_GZ_KL, 1, tmp);
                    System.out.println("KL_ID: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennejNr(idSesji, api.PIK_GZ_KL, 99, tmp);
                    System.out.println("KL_TypZmiany: " + tmp[0] );
                } while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_WpiszKomunikat0_OLD() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String rachNadawcy = "", dataKomunikatu = "";

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();

        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);

        rachNadawcy = wczytajZmienna("oNRB");
        dataKomunikatu = dateFormat.format(date);
        
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Strumien, defAPI.Z_KWOTA , "20");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_ID, defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypZlecenia, defAPI.Z_RACH , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KodDokumentu, defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KorespondentNad, defAPI.Z_RACH , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekNadawcy, defAPI.Z_BUFOR , rachNadawcy);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaNadawcy, defAPI.Z_MEMO , "PIK WK");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_AdresatFinalny, defAPI.Z_RACH , "21300004");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekAdresata, defAPI.Z_BUFOR , "63213000042001019529770001");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaAdresata, defAPI.Z_MEMO , "KLIENT testowy");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Kwota, defAPI.Z_KWOTA , "123.89");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Waluta, defAPI.Z_BUFOR , "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_DataKomunikatu, defAPI.Z_DATA , dataKomunikatu);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_OpisDodatkowy, defAPI.Z_MEMO , "Przelew \u015Brodk\u00F3w 1\nPrzelew \u015Brodk\u00F3w 2\nPrzelew \u015Brodk\u00F3w 3");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Rozliczenie, defAPI.Z_RACH , "0");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Inf_Miedzybank, defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NIP, defAPI.Z_BUFOR , "1111111111");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypSkladki, defAPI.Z_BUFOR , "51");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypIdentyfik, defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Identyfikator, defAPI.Z_BUFOR , "GAI981790");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypWplaty, defAPI.Z_BUFOR , "D");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Deklaracja, defAPI.Z_BUFOR , "201201");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NrDeklaracji, defAPI.Z_BUFOR , "01");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.NrDecyzji_Umowy", defAPI.Z_BUFOR , "01aqq");
        //PSD
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzyPSD", defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.DataPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzasPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.Kurs", defAPI.Z_BUFOR , "0.0");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.ListaOplat", defAPI.Z_BUFOR , "<Dysp.Oplaty></Dysp.Oplaty>");
        
        wynik = api.PIK_WpiszKomunikat(idSesji, defAPI.ELX_RK_UZNANIE_ZUS, "WWW");
        System.out.println("PIK_WpiszKomunikat  : " + wynik);            

        String[] wartosc = new String[1];
        
        if(wynik == WYNIK_OK)            
        {
            api.PIK_ZeZmiennej(idSesji, defAPI.ELIXIR_ID, wartosc);
            System.out.println("\tELIXIR_ID: " + wartosc[0] );
        }
        else
        {
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(0);            
        }
        separator();
         try{
            Thread.sleep(LongSleep);
        }catch (InterruptedException e){
        }
    }
    
    void PIK_WpiszKomunikat6() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String rachNadawcy = "", dataKomunikatu = "";

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();

        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);

        rachNadawcy = wczytajZmienna("oNRB");
        dataKomunikatu = dateFormat.format(date);
        
        String NRB_US = NRB_US_NIP();
        
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Strumien, defAPI.Z_KWOTA , "20");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_ID, defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypZlecenia, defAPI.Z_RACH , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KodDokumentu, defAPI.Z_BUFOR , "71");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KorespondentNad, defAPI.Z_RACH , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekNadawcy, defAPI.Z_BUFOR , rachNadawcy);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaNadawcy, defAPI.Z_MEMO , "PIK WK");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_AdresatFinalny, defAPI.Z_RACH , "10100071");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekAdresata, defAPI.Z_BUFOR , NRB_US);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaAdresata, defAPI.Z_MEMO , "KLIENT testowy");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Kwota, defAPI.Z_KWOTA , "123.89");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Waluta, defAPI.Z_BUFOR , "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_DataKomunikatu, defAPI.Z_DATA , dataKomunikatu);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_OpisDodatkowy, defAPI.Z_MEMO , "Przelew \u015Brodk\u00F3w 1\nPrzelew \u015Brodk\u00F3w 2\nPrzelew \u015Brodk\u00F3w 3");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Rozliczenie, defAPI.Z_RACH , "0");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Inf_Miedzybank, defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypIdentyfik, defAPI.Z_BUFOR , "N");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Identyfikator, defAPI.Z_BUFOR , "1111111111");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Okres, defAPI.Z_BUFOR , "20R");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Symbol, defAPI.Z_BUFOR , "PIT");
        //PSD
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzyPSD", defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.DataPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzasPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.Kurs", defAPI.Z_BUFOR , "0.0");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.ListaOplat", defAPI.Z_BUFOR , "<Dysp.Oplaty></Dysp.Oplaty>");
        
        wynik = api.PIK_WpiszKomunikat(idSesji, defAPI.ELX_RK_PLATNOSC_DO_US, "WWW");
        System.out.println("PIK_WpiszKomunikat  : " + wynik);            

        String[] wartosc = new String[1];
        
        if(wynik == WYNIK_OK)            
        {
            api.PIK_ZeZmiennej(idSesji, defAPI.ELIXIR_ID, wartosc);
            System.out.println("\tELIXIR_ID: " + wartosc[0] );
        }
        else
        {
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(1);            
        }
        separator();
         try{
            Thread.sleep(LongSleep);
        }catch (InterruptedException e){
        }
    }
    
    void PIK_WpiszKomunikat6_OLD() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String rachNadawcy = "", dataKomunikatu = "";

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();

        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);

        rachNadawcy = wczytajZmienna("oNRB");
        dataKomunikatu = dateFormat.format(date);
        
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Strumien, defAPI.Z_KWOTA , "20");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_ID, defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypZlecenia, defAPI.Z_RACH , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KodDokumentu, defAPI.Z_BUFOR , "71");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KorespondentNad, defAPI.Z_RACH , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekNadawcy, defAPI.Z_BUFOR , rachNadawcy);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaNadawcy, defAPI.Z_MEMO , "PIK WK");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_AdresatFinalny, defAPI.Z_RACH , "10101528");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekAdresata, defAPI.Z_BUFOR , "82101015280010652223000000");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaAdresata, defAPI.Z_MEMO , "KLIENT testowy");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Kwota, defAPI.Z_KWOTA , "123.89");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Waluta, defAPI.Z_BUFOR , "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_DataKomunikatu, defAPI.Z_DATA , dataKomunikatu);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_OpisDodatkowy, defAPI.Z_MEMO , "Przelew \u015Brodk\u00F3w 1\nPrzelew \u015Brodk\u00F3w 2\nPrzelew \u015Brodk\u00F3w 3");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Rozliczenie, defAPI.Z_RACH , "0");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Inf_Miedzybank, defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypIdentyfik, defAPI.Z_BUFOR , "N");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Identyfikator, defAPI.Z_BUFOR , "1111111111");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Okres, defAPI.Z_BUFOR , "12R");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Symbol, defAPI.Z_BUFOR , "PIT");
        //PSD
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzyPSD", defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.DataPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzasPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.Kurs", defAPI.Z_BUFOR , "0.0");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.ListaOplat", defAPI.Z_BUFOR , "<Dysp.Oplaty></Dysp.Oplaty>");
        
        wynik = api.PIK_WpiszKomunikat(idSesji, defAPI.ELX_RK_PLATNOSC_DO_US, "WWW");
        System.out.println("PIK_WpiszKomunikat  : " + wynik);            

        String[] wartosc = new String[1];
        
        if(wynik == WYNIK_OK)            
        {
            api.PIK_ZeZmiennej(idSesji, defAPI.ELIXIR_ID, wartosc);
            System.out.println("\tELIXIR_ID: " + wartosc[0] );
        }
        else
        {
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(0);            
        }
        separator();
         try{
            Thread.sleep(LongSleep);
        }catch (InterruptedException e){
        }
    }
    
    void PIK_WykonajProcedure_PobierzInfo_CUR() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String IDKlienta = "";
        IDKlienta = wczytajZmienna("oIdKl");
        
        wynik = api.PIK_DoZmiennej(idSesji, "TypOperacji",defAPI.Z_BUFOR, "CUR");
        wynik = api.PIK_DoZmiennej(idSesji, "RACH_B.ID_KL_DEF",defAPI.Z_BUFOR, IDKlienta);
        wynik = api.PIK_DoZmiennej(idSesji, "RACH_B.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "PobierzInfo");
        System.out.println("PIK_WykonajProcedure PobierzInfo_CUR: " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "RACH_B.Lista", lista);
            System.out.println("RACH_B.Lista : " + lista[0]);
        }
        else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_DajSaldo() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String modulo = "", konto = "", uwaga = "";
        modulo = wczytajZmienna("iModZS");
        konto = wczytajZmienna("iKonZS");
        uwaga = wczytajZmienna("iUwaZS");
        
        String[] saldo = new String[1];
        wynik = api.PIK_DajSaldo(idSesji, modulo, konto, uwaga, "", DataKsiegowaDef, saldo);
        System.out.println("PIK_DajSaldo : " + wynik);
        
        if(wynik == WYNIK_OK){
            System.out.println("Saldo: " + saldo[0]);
        }
        else{
            wyswietlBlad(1);
        }
    }
    
    void PIK_Autoryzacja7() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String NRB = "", iNrAut = "";
        NRB = wczytajZmienna("oNRB");
        iNrAut = wczytajZmienna("iNrAutoryzacjiOnLine");
        
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.IDRachunku",defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej(idSesji, "AUTO_RachZlec",defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.RodzajAutoryz",defAPI.Z_RACH, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.Kwota",defAPI.Z_KWOTA, "100.00");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.KwotaOpłaty",defAPI.Z_KWOTA, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.NrZewnętrzny",defAPI.Z_BUFOR, iNrAut);
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.IDGdzie",defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.Zlecający",defAPI.Z_BUFOR, "SERWIS");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.IDKtoWpisał",defAPI.Z_BUFOR, "16702");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.KanałAutoryzacji",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.MiejsceWypłaty",defAPI.Z_BUFOR, "");
        
        String[] status = new String[1];
        wynik = api.PIK_Autoryzacja(idSesji, api.AU_A_AUTORYZACJA_SYST_ZEWN, status);
        System.out.println("PIK_Autoryzacja : " + wynik);
        
        if(wynik == WYNIK_OK){
            System.out.println("Status autoryzacji : " + status[0]);
        }else{
            System.out.println("Status błędnej autoryzacji : " + status[0]);
            wyswietlBlad(1);
        }
        
        zapiszZmienna("iNrAutoryzacjiOnLine", ((Integer.parseInt(iNrAut)+1)+"") );
        separator();
    }
    
    void PIK_Autoryzacja7_Typ10() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String NRB = "", iNrAut = "";
        NRB = wczytajZmienna("oNRB");
        iNrAut = wczytajZmienna("iNrAutoryzacjiOnLine");
        
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.IDRachunku",defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej(idSesji, "AUTO_RachZlec",defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.RodzajAutoryz",defAPI.Z_RACH, "10");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.Kwota",defAPI.Z_KWOTA, "321.15");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.KwotaOpłaty",defAPI.Z_KWOTA, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.NrZewnętrzny",defAPI.Z_BUFOR, iNrAut);
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.IDGdzie",defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.Zlecający",defAPI.Z_BUFOR, "SERWIS");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.IDKtoWpisał",defAPI.Z_BUFOR, "16702");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.KanałAutoryzacji",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.MiejsceWypłaty",defAPI.Z_BUFOR, "");
        
        String[] status = new String[1];
        wynik = api.PIK_Autoryzacja(idSesji, api.AU_A_AUTORYZACJA_SYST_ZEWN, status);
        System.out.println("PIK_Autoryzacja : " + wynik);
        
        if(wynik == WYNIK_OK){
            System.out.println("Status autoryzacji : " + status[0]);
        }else{
            System.out.println("Status błędnej autoryzacji : " + status[0]);
            wyswietlBlad(1);
        }
        
        zapiszZmienna("iNrAutoryzacjiOnLine", ((Integer.parseInt(iNrAut)+1)+"") );
        separator();
    }
    
    void PIK_DajListeAutoryzacji() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0, zgodnosc = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String NRB = "", idTran = "";
        NRB = wczytajZmienna("oNRB");
        idTran = wczytajZmienna("oRkTranID");
        
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.Akcja", defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.NRB", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.Suma", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.IDAutoryzacji", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.TypTransakcji", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.Kwota", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.NrKarty", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.DataCzasGMT", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.DataCzasLok", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.NazwaMerchanta", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.MiastoMerchanta", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.KrajMerchanta", defAPI.Z_BUFOR, "");
        
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.TypAutoryzacji", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.RodzajAutoryz", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Auto.NrZewnętrzny", defAPI.Z_BUFOR, "");
        
        
        wynik = api.PIK_DajListeAutoryzacji(idSesji, idTran);
        System.out.println("PIK_DajListeAutoryzacji : " + wynik);
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("PIK_LiczbaRekordow : " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                String[] tmp = new String[1];
                int i = 0;
                do{
                    i += 1;
                    System.out.println(i + " : ");
                    zgodnosc = 0;
                    
                    wynik = api.PIK_ZeZmiennej(idSesji, "Auto.IDAutoryzacji", tmp);
                    System.out.println("Auto.IDAutoryzacji: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Auto.TypTransakcji", tmp);
                    System.out.println("Auto.TypTransakcji: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Auto.Kwota", tmp);
                    System.out.println("Auto.Kwota: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Auto.NrKarty", tmp);
                    System.out.println("Auto.NrKarty: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Auto.DataCzasGMT", tmp);
                    System.out.println("Auto.DataCzasGMT: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Auto.DataCzasLok", tmp);
                    System.out.println("Auto.DataCzasLok: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Auto.NazwaMerchanta", tmp);
                    System.out.println("Auto.NazwaMerchanta: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Auto.MiastoMerchanta", tmp);
                    System.out.println("Auto.MiastoMetchanta: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Auto.KrajMerchanta", tmp);
                    System.out.println("Auto.KrajMerchanta: " + tmp[0] );
                    wynik = api.PIK_ZeZmiennej(idSesji, "Auto.TypAutoryzacji", tmp);
                    System.out.println("Auto.TypAutoryzacji: " + tmp[0] );
                    if(tmp[0].equals("SZ"))
                        zgodnosc++;
                    wynik = api.PIK_ZeZmiennej(idSesji, "Auto.RodzajAutoryz", tmp);
                    System.out.println("Auto.RodzajAutoryz: " + tmp[0] );
                    if(tmp[0].equals("10"))
                        zgodnosc++;
                    wynik = api.PIK_ZeZmiennej(idSesji, "Auto.NrZewnętrzny", tmp);
                    System.out.println("Auto.NrZewnętrzny: " + tmp[0] );
                    if(zgodnosc == 2)
                        zapiszZmienna("oNrZewAutoRozl", tmp[0]);
                    
                } while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
                wynik = api.PIK_ZeZmiennej(idSesji, "Auto.Suma", tmp);
                System.out.println("Auto.Suma: " + tmp[0] );
            }
        }
        else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_WykonajProcedure_ObslugaParWyciagu() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String NRB = "";
        NRB = wczytajZmienna("oNRB");
        
        wynik = api.PIK_DoZmiennej(idSesji, "OW.Rachunek",defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej(idSesji, "OW.WyciagTyp",defAPI.Z_BUFOR, "BEZ WYCIĄGU");
        wynik = api.PIK_DoZmiennej(idSesji, "OW.WyciagOkres",defAPI.Z_BUFOR, "PO OPERACJI");
        wynik = api.PIK_DoZmiennej(idSesji, "OW.WyciagWysylac",defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "OW.WycigDniOkresu",defAPI.Z_KWOTA, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "OW.WyciagKorSeryjna",defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej(idSesji, "OW.KodBledu",defAPI.Z_BUFOR, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "ObslugaParWyciagu");
        System.out.println("PIK_WykonajProcedure ObslugaParWyciagu : " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej(idSesji, "OW.KodBledu", lista);
            System.out.println("OW.KodBledu : " + lista[0]);
            
        }
        else{
            wyswietlBlad(1);
        }
        separator();    
    }
    
    void PIK_WykonajProcedure_PobierzKosztyDyspozycji() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String NRB = "";
        NRB = wczytajZmienna("oNRB");
        
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.TypDyspozycji",defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.System",defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.TrybWykonania",defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.RodzajDyspozycji",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.TypZleceniaSt",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.TypSwift",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.Kwota",defAPI.Z_KWOTA, "1000.00");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.Waluta",defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.NRBNadawcy",defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.NRBOdbiorcy",defAPI.Z_BUFOR, "38114011664120141433954074");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.KanalDystrybucji",defAPI.Z_BUFOR, "WWW");
        
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.KursPrzewalutowania",defAPI.Z_KWOTA, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.ListaOplat",defAPI.Z_MEMO, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.KodBledu",defAPI.Z_KWOTA, "");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.OpisBledu",defAPI.Z_BUFOR, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "PobierzKosztyDyspozycji");
        System.out.println("PIK_WykonajProcedure PobierzKosztyDyspozycji : " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        
        String[] out = new String[1];
        
        if(wynik == WYNIK_OK){
            api.PIK_ZeZmiennej(idSesji, "Dysp.KursPrzewalutowania", out);
            System.out.println("Dysp.KursPrzewalutowania: " + out[0]);
            api.PIK_ZeZmiennej(idSesji, "Dysp.ListaOplat", out);
            System.out.println("Dysp.ListaOplat: " + out[0]);
            api.PIK_ZeZmiennej(idSesji, "Dysp.KodBledu", out);
            System.out.println("Dysp.KodBledu: " + out[0]);
            api.PIK_ZeZmiennej(idSesji, "Dysp.OpisBledu", out);
            System.out.println("Dysp.OpisBledu: " + out[0]);
        }
        else{
            wyswietlBlad(1);
            api.PIK_ZeZmiennej(idSesji, "Dysp.KodBledu", out);
            System.out.println("Dysp.KodBledu: " + out[0]);
            api.PIK_ZeZmiennej(idSesji, "Dysp.OpisBledu", out);
            System.out.println("Dysp.OpisBledu: " + out[0]);
        }
        separator();
    }
    
    void PIK_ZleceniaStale1() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String modulo = "", konto = "", uwaga = "";
        modulo = wczytajZmienna("iModZS");
        konto = wczytajZmienna("iKonZS");
        uwaga = wczytajZmienna("iUwaZS");
        
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypZlecenia",defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Powtarzalność",defAPI.Z_BUFOR, "3");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDR-ekWłasny",defAPI.Z_BUFOR, ""); 
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.ModuloKlienta",defAPI.Z_BUFOR, modulo);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Konto",defAPI.Z_BUFOR, konto);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Uwaga",defAPI.Z_BUFOR, uwaga);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.RodzajZlecenia",defAPI.Z_BUFOR, "Prąd");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Kwota",defAPI.Z_BUFOR, "123.45");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Waluta",defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.BankAdresat",defAPI.Z_BUFOR, "11401166");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.R-ekAdresata",defAPI.Z_BUFOR, "38114011664120141433954074");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDR-ekAdresata",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Adresat",defAPI.Z_BUFOR, "Adresat\nZlecenia\nStałego\nRegres");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Tytuł",defAPI.Z_BUFOR, "Tytuł\nZlecenia\nStałego\nRegres");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.NIP",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDP",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypSkładki",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.NazwaNadawcy",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypIdentyfik.",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Identyfikator",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypWpłaty",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Deklaracja",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.NrDeklaracji",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Priorytet",defAPI.Z_BUFOR, "300");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.DzieńRealizacji",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypDnia",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.CoIlePowtarzać",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.MiesiącStartu",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.MiesiącKońca",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.DataRealizacji",defAPI.Z_BUFOR, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.KanałDystrybucji",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypOpłaty",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Opłata",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.MusiZatwierdzić",defAPI.Z_BUFOR, "2");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.ObsługaZaległych",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypopłatyZaleg.",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.OpłataZaległości",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypZawieszenia",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.ZawieszeniaOd",defAPI.Z_BUFOR, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.ZawieszenieDo",defAPI.Z_BUFOR, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.OpisZawieszenia",defAPI.Z_BUFOR, "Bo tak");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.BlokadaŚr",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.KanałWgPro",defAPI.Z_BUFOR, "WWW");
        
        wynik = api.PIK_ZleceniaStale(idSesji, api.ZS_A_DODANIE, "");
        System.out.println("PIK_ZleceniaStale : " + wynik);
        String[] wartosc = new String[1];
        
        if(wynik == WYNIK_OK){

        }else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_ZleceniaStale2() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String idZlec = "";
        idZlec = wczytajZmienna("oIdZlecSta");
        
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.KanałDystrybucji",defAPI.Z_BUFOR, "WWW");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.RodzajZlecenia",defAPI.Z_BUFOR, "Cyklon-B");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Kwota",defAPI.Z_BUFOR, "987.65");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Tytuł",defAPI.Z_BUFOR, "Zmiana\nZlecenia\nStałego\nRegres");
        
        wynik = api.PIK_ZleceniaStale(idSesji, api.ZS_A_ZMIANA, idZlec);
        System.out.println("PIK_ZleceniaStale : " + wynik);
        
        if(wynik == WYNIK_OK){

        }else{
            wyswietlBlad(1);
        }
        separator();
        
    }
    
    void PIK_ZleceniaStale4() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String idZle = "";
        idZle = wczytajZmienna("oIdZlecSta");
        
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_STATUS",defAPI.Z_BUFOR, "");
                
        wynik = api.PIK_ZleceniaStale(idSesji, api.ZS_A_HISTORIA, idZle);
        System.out.println("PIK_ZleceniaStale : " + wynik);
        String[] out = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("Liczba rekordów: " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                do{
                    wynik = api.PIK_ZeZmiennej(idSesji, "OPER_STATUS", out);
                    System.out.println("OPER_STATUS: " + out[0]);
                }while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_ZleceniaStale6() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String idZle = "";
        idZle = wczytajZmienna("oIdZlecSta");
        
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.RodzajZlecenia",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Kwota",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Tytuł",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.KwotaVAT",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDC",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.INV",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TXT",defAPI.Z_BUFOR, "");
        
        wynik = api.PIK_ZleceniaStale(idSesji, api.ZS_A_POBRANIE, idZle);
        System.out.println("PIK_ZleceniaStale : " + wynik);
        String[] out = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.RodzajZlecenia", out);
            System.out.println("ZlecSt.RodzajZlecenia : " + out[0]);
            wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.Kwota", out);
            System.out.println("ZlecSt.Kwota : " + out[0]);
            wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.Tytuł", out);
            System.out.println("ZlecSt.Tytuł : " + out[0]);
            wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.KwotaVAT", out);
            System.out.println("ZlecSt.KwotaVAT : " + out[0]);
            wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.IDC", out);
            System.out.println("ZlecSt.IDC : " + out[0]);
            wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.INV", out);
            System.out.println("ZlecSt.INV : " + out[0]);
            wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.TXT", out);
            System.out.println("ZlecSt.TXT : " + out[0]);
        }else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_ZleceniaStale8() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String modulo = "";
        modulo = wczytajZmienna("iModZS");
        
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.ModuloKlienta",defAPI.Z_BUFOR, modulo);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.RodzajZlecenia",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Kwota",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Tytuł",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.ID",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Status",defAPI.Z_BUFOR, "");
        
        wynik = api.PIK_ZleceniaStale(idSesji, api.ZS_A_CZYNNE_KL, "");
        System.out.println("PIK_ZleceniaStale : " + wynik);
        String[] out = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("Liczba rekordów: " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                int i = 0;
                do{
                    System.out.println(++i + " : ");
                    wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.ID", out);
                    System.out.println("ZlecSt.ID : " + out[0]);
                    zapiszZmienna("oIdZlecSta", out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.Status", out);
                    System.out.println("ZlecSt.Status : " + out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.RodzajZlecenia", out);
                    System.out.println("ZlecSt.RodzajZlecenia : " + out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.Kwota", out);
                    System.out.println("ZlecSt.Kwota : " + out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.Tytuł", out);
                    System.out.println("ZlecSt.Tytuł : " + out[0]);
                }while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_ZleceniaStale11() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.DataRealizacji",defAPI.Z_BUFOR, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.R-ekZleceniodawcy",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Kwota",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Tytuł",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.NrKolejny",defAPI.Z_BUFOR, "");
        
        wynik = api.PIK_ZleceniaStale(idSesji, api.ZS_A_NIEZREALIZOWANE, "");
        System.out.println("PIK_ZleceniaStale : " + wynik);
        String[] out = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("Liczba rekordów: " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                int i = 0;
                do{
                    System.out.println(++i + " : ");
                    wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.NrKolejny", out);
                    System.out.println("ZlecSt.NrKolejny : " + out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.R-ekZleceniodawcy", out);
                    System.out.println("ZlecSt.R-ekZleceniodawcy : " + out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.Kwota", out);
                    System.out.println("ZlecSt.Kwota : " + out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "ZlecSt.Tytuł", out);
                    System.out.println("ZlecSt.Tytuł : " + out[0]);
                }while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }else{
            wyswietlBlad(1);
        }
        try{
            Thread.sleep(LongSleep);
        }catch(InterruptedException e){}
        separator();
    }
    
    void PIK_WykonajProcedure_PobierzInfo_WSR() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String modulo = "", konto = "", uwaga = "";
        modulo = wczytajZmienna("iModZS");
        konto = wczytajZmienna("iKonZS");
        uwaga = wczytajZmienna("iUwaZS");
        
        wynik = api.PIK_DoZmiennej(idSesji, "TypOperacji",defAPI.Z_BUFOR, "WSR");
        wynik = api.PIK_DoZmiennej(idSesji, "SRODKI.Modulo",defAPI.Z_BUFOR, modulo);
        wynik = api.PIK_DoZmiennej(idSesji, "SRODKI.Konto",defAPI.Z_BUFOR, konto);
        wynik = api.PIK_DoZmiennej(idSesji, "SRODKI.Uwaga",defAPI.Z_BUFOR, uwaga);
        wynik = api.PIK_DoZmiennej(idSesji, "SRODKI.PolePom",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "SRODKI.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "PobierzInfo");
        System.out.println("PIK_WykonajProcedure PobierzInfo_WSR : " + wynik);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "SRODKI.Lista", lista);
            System.out.println("SRODKI.Lista : " + lista[0]);
        }else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_WykonajProcedure_PobierzInfo_DEP() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String IDKlienta = "";
        IDKlienta = wczytajZmienna("oIdKl");
        
        wynik = api.PIK_DoZmiennej(idSesji, "TypOperacji",defAPI.Z_BUFOR, "DEP");
        wynik = api.PIK_DoZmiennej(idSesji, "LOKATA.ID_KL_DEF",defAPI.Z_BUFOR, IDKlienta);
        wynik = api.PIK_DoZmiennej(idSesji, "LOKATA.ID_LOKATY",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "LOKATA.DODATK_POWIAZ",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "LOKATA.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "PobierzInfo");
        System.out.println("PIK_WykonajProcedure PobierzInfo_DEP: " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "LOKATA.Lista", lista);
            System.out.println("LOKATA.Lista : " + lista[0]);
        }
        else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_WykonajProcedure_PobierzInfo_KLD() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String IDKlienta = "";
        IDKlienta = wczytajZmienna("oIdKl");
        
        wynik = api.PIK_DoZmiennej(idSesji, "TypOperacji",defAPI.Z_BUFOR, "KLD");
        wynik = api.PIK_DoZmiennej(idSesji, "KL.ID",defAPI.Z_BUFOR, IDKlienta);
        wynik = api.PIK_DoZmiennej(idSesji, "KL.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "PobierzInfo");
        System.out.println("PIK_WykonajProcedure PobierzInfo_KLD: " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "KL.Lista", lista);
            System.out.println("KL.Lista : " + lista[0]);
        }
        else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_WykonajProcedure_PobierzInfo_KLM() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String IDKlienta = "";
        IDKlienta = wczytajZmienna("oModKl");
        
        wynik = api.PIK_DoZmiennej(idSesji, "TypOperacji",defAPI.Z_BUFOR, "KLM");
        wynik = api.PIK_DoZmiennej(idSesji, "KL.ID",defAPI.Z_BUFOR, IDKlienta);
        wynik = api.PIK_DoZmiennej(idSesji, "KL.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "PobierzInfo");
        System.out.println("PIK_WykonajProcedure PobierzInfo_KLM: " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "KL.Lista", lista);
            System.out.println("KL.Lista : " + lista[0]);
        }
        else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_WykonajProcedure_PobierzInfo_KLI() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String IDKlienta = "";
        IDKlienta = wczytajZmienna("oIdKl");
        
        wynik = api.PIK_DoZmiennej(idSesji, "TypOperacji",defAPI.Z_BUFOR, "KLI");
        wynik = api.PIK_DoZmiennej(idSesji, "KL.ID",defAPI.Z_BUFOR, IDKlienta);
        wynik = api.PIK_DoZmiennej(idSesji, "KL.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "PobierzInfo");
        System.out.println("PIK_WykonajProcedure PobierzInfo_KLI: " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "KL.Lista", lista);
            System.out.println("KL.Lista : " + lista[0]);
        }
        else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_WykonajProcedure_PobierzInfo_LKR() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        String IDKlienta = "";
        IDKlienta = wczytajZmienna("oIdKl");
        
        wynik = api.PIK_DoZmiennej(idSesji, "TypOperacji",defAPI.Z_BUFOR, "LKR");
        wynik = api.PIK_DoZmiennej(idSesji, "KRLST.ID_KL_DEF",defAPI.Z_BUFOR, IDKlienta);
        wynik = api.PIK_DoZmiennej(idSesji, "KRLST.POWIAZ_MOD_KL",defAPI.Z_BUFOR, IDKlienta);
        wynik = api.PIK_DoZmiennej(idSesji, "KRLST.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "PobierzInfo");
        System.out.println("PIK_WykonajProcedure PobierzInfo_LKR: " + wynik);
        
        int stat = api.PIK_DajStatusTransakcji(idSesji);
        System.out.println("PIK_DajStatusTransakcji : " + stat);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "KRLST.Lista", lista);
            System.out.println("KRLST.Lista : " + lista[0]);
        }
        else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void wplataSrodkowNaRB() throws IOException{
        String NRB = wczytajZmienna("oNRB");
        
        System.out.println("Wpłać 10 000 PLN na RB : " + NRB);
        System.out.println("Po czym nacisnij enter");
        
    }
    
    void PIK_Rachunki5() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String NRB = "";
        
        NRB = wczytajZmienna("oNRB");
        
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.NRB", defAPI.Z_BUFOR , NRB);
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.Modulo", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.Konto", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.Uwaga", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.ID", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.TranID", defAPI.Z_BUFOR , ""); 
        
        wynik = api.PIK_Rachunki(idSesji, defAPI.RK_A_LISTA_NRB);
        System.out.println("PIK_Rachunki  : " + wynik); 
        
        String[] out = new String[5];
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("Liczba rekordów: " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                int i = 0;
                do{
                    System.out.println(++i + " : ");
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.Modulo", out);
                    System.out.println("Rk.Modulo : " + out[0]);
                    zapiszZmienna("iModZS", out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.Konto", out);
                    System.out.println("Rk.Konto : " + out[0]);
                    zapiszZmienna("iKonZS", out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.Uwaga", out);
                    System.out.println("Rk.Uwaga : " + out[0]);
                    zapiszZmienna("iUwaZS", out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.ID", out);
                    System.out.println("Rk.ID : " + out[0]);
                    zapiszZmienna("iIDRB", out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.TranID", out);
                    System.out.println("Rk.TranID : " + out[0]);
                    zapiszZmienna("oRkTranID", out[0]);
                    
                }while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_Rachunki5KR() throws FileNotFoundException, IOException{
        int wynik = 0;
        String NRB = "";
        
        NRB = wczytajZmienna("oNRBKr");
        
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.NRB", defAPI.Z_BUFOR , NRB);
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.Modulo", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.Konto", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.Uwaga", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.ID", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.TranID", defAPI.Z_BUFOR , ""); 
        
        wynik = api.PIK_Rachunki(idSesji, defAPI.RK_A_LISTA_NRB);
        System.out.println("PIK_Rachunki  : " + wynik); 
        
        String[] out = new String[5];
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("Liczba rekordów: " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                int i = 0;
                do{
                    System.out.println(++i + " : ");
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.Modulo", out);
                    System.out.println("Rk.Modulo : " + out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.Konto", out);
                    System.out.println("Rk.Konto : " + out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.Uwaga", out);
                    System.out.println("Rk.Uwaga : " + out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.ID", out);
                    System.out.println("Rk.ID : " + out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.TranID", out);
                    System.out.println("Rk.TranID : " + out[0]);
                    
                }while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }else{
            wyswietlBlad(1);
        }
        separator();
    }

    void PIK_Klient3_ROs() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String nazwa = "", typFormatki = "", adrKodPocztowy = "", adrMiejscowosc = "", 
                adrUlica = "", typKlienta = "", nazwisko = "", imie_1 = "", pesel = "";
        
        String[] ID = new String[100];
        String[] Mod = new String[100];
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        nazwa = "Rolnik_";
        typFormatki = "2";
        adrKodPocztowy ="11-111";
        adrMiejscowosc = "Miejscowo\u015B\u0107 KL";
        adrUlica = "Ulica KL";
        typKlienta = "19";
        nazwisko = "KL_Nazwisko ";
        imie_1 = "KL_Imie_1";
        
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Nazwa", defAPI.Z_BUFOR , nazwa);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.TypFormatki", defAPI.Z_BUFOR , typFormatki);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Adr_KodPocztowy", defAPI.Z_BUFOR , adrKodPocztowy);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Adr_Miejscowość", defAPI.Z_BUFOR , adrMiejscowosc);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Adr_Ulica", defAPI.Z_BUFOR , adrUlica);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.TypKlienta", defAPI.Z_BUFOR , typKlienta);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Nazwisko", defAPI.Z_BUFOR , nazwisko);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Imię_1", defAPI.Z_BUFOR , imie_1);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.NrKlienta", defAPI.Z_BUFOR , "");
        
        //Wywołanie
        wynik = api.PIK_Klient(idSesji, defAPI.KL_A_DODANIE);
        System.out.println("PIK_Klient  : " + wynik);
        
        if(wynik == WYNIK_OK){
            System.out.println("wynik == WYNIK_OK");
            api.PIK_ZeZmiennej(idSesji, "Klnt1.ID", ID);
            System.out.println("\tKlnt1.ID: " + ID[0] );
            zapiszZmienna("oIdKl", ID[0]);
            api.PIK_ZeZmiennej(idSesji, "Klnt1.NrKlienta", Mod);
            System.out.println("\tKlnt1.NrKlienta: " + Mod[0] );
            zapiszZmienna("oModKl", Mod[0]);
        }
        else{
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(0);
        }
        separator();

    }
    
    void PIK_Klient3_RF() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String nazwa = "", typFormatki = "", adrKodPocztowy = "", adrMiejscowosc = "", 
                adrUlica = "", typKlienta = "", nazwisko = "", imie_1 = "", pesel = "", nrKolejny = "";
        
        String[] ID = new String[100];
        String[] Mod = new String[100];
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        nrKolejny = wczytajZmienna("iNrKolejny");
        
        nazwa = "Rolnik_" + nrKolejny;
        typFormatki = "3";
        adrKodPocztowy ="11-111";
        adrMiejscowosc = "Miejscowo\u015B\u0107 KL";
        adrUlica = "Ulica KL";
        typKlienta = "19";
        nazwisko = "Rolnik_" + nrKolejny;
        imie_1 = "Rolnik_" + nrKolejny;
        
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Nazwa", defAPI.Z_BUFOR , nazwa);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.TypFormatki", defAPI.Z_BUFOR , typFormatki);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Adr_KodPocztowy", defAPI.Z_BUFOR , adrKodPocztowy);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Adr_Miejscowość", defAPI.Z_BUFOR , adrMiejscowosc);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.Adr_Ulica", defAPI.Z_BUFOR , adrUlica);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.TypKlienta", defAPI.Z_BUFOR , typKlienta);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.NazwaKrótka", defAPI.Z_BUFOR , nazwisko);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.NazwaPełna", defAPI.Z_BUFOR , imie_1);
        wynik = api.PIK_DoZmiennej(idSesji, "Klnt1.NrKlienta", defAPI.Z_BUFOR , "");
        
//Wywołanie
        wynik = api.PIK_Klient(idSesji, defAPI.KL_A_DODANIE);
        System.out.println("PIK_Klient  : " + wynik);
        
        if(wynik == WYNIK_OK){
            System.out.println("wynik == WYNIK_OK");
            api.PIK_ZeZmiennej(idSesji, "Klnt1.ID", ID);
            System.out.println("\tKlnt1.ID: " + ID[0] );
            zapiszZmienna("oIdKl", ID[0]);
            api.PIK_ZeZmiennej(idSesji, "Klnt1.NrKlienta", Mod);
            System.out.println("\tKlnt1.NrKlienta: " + Mod[0] );
            zapiszZmienna("oModKl", Mod[0]);
        }
        else{
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(0);
        }
        
        nrKolejny = "" + ((Integer.parseInt(nrKolejny)) + 1);
        zapiszZmienna("iNrKolejny", nrKolejny );
        separator();

    }
    
    void PIK_UmowaKR13() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej (idSesji, "KR_WalutaKredytu", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_KwotaBrutto", defAPI.Z_KWOTA, "100000");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Oprc_Typ", defAPI.Z_RACH, "2");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Oprc_Tabela", defAPI.Z_BUFOR, "10");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Oprc_Mnoznik", defAPI.Z_KWOTA, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Oprc_Podstawa", defAPI.Z_KWOTA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Oprc_Tryb", defAPI.Z_RACH, "0");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Jednostka", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_DataWyplaty", defAPI.Z_DATA, DataKsiegowaDefP1);
        wynik = api.PIK_DoZmiennej (idSesji, "KR_TypHarmonogramu", defAPI.Z_BUFOR, "02"); 
        wynik = api.PIK_DoZmiennej (idSesji, "KR_LiczbaRat", defAPI.Z_RACH, "12");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_OkresRaty", defAPI.Z_RACH, "2");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_LiczbaOkrRaty", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_DataRaty", defAPI.Z_DATA, DataKsiegowaDefP1P1M);
        wynik = api.PIK_DoZmiennej (idSesji, "KR_LiczbaRatOds", defAPI.Z_RACH, "12");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_OkresRatyOds", defAPI.Z_RACH, "2");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_LiczbaOkrRatyOds", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_KalendarzSposob", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_DataRatyOds", defAPI.Z_DATA, DataKsiegowaDefP1P1M);
        wynik = api.PIK_DoZmiennej (idSesji, "KR_ZaokraglenieRat", defAPI.Z_KWOTA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_RataRoznicowa", defAPI.Z_BUFOR, "O");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_BladNr", defAPI.Z_RACH, ""); 
        wynik = api.PIK_DoZmiennej (idSesji, "KR_BladOpis", defAPI.Z_MEMO, ""); 
        wynik = api.PIK_DoZmiennej (idSesji, "SCHEDULE.Lista", defAPI.Z_MEMO, ""); 
        wynik = api.PIK_DoZmiennej (idSesji, "KR_HarmOdsetek1_31", defAPI.Z_BUFOR, "NIE");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_KredytKonsumencki", defAPI.Z_BUFOR, "KK3");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_DataTranszy01", defAPI.Z_DATA, DataKsiegowaDefP1); 
        wynik = api.PIK_DoZmiennej (idSesji, "KR_KwotaTranszy01", defAPI.Z_KWOTA, "100000"); 
        
        wynik = api.PIK_UmowaKR(idSesji, api.KR_A_SYMULACJA);
        System.out.println("PIK_UmowaKR(13) : " + wynik);
        String out [] = new String[10];
        
        if (wynik == 0){
            System.out.println("PIK_UmowaKR(13)  : " + wynik);
            wyswietlBlad(1);
            api.PIK_ZeZmiennej(idSesji, "KR_BladNr", out);
            System.out.println("Numer bledu: " + out[0]);
            api.PIK_ZeZmiennej(idSesji, "KR_BladOpis", out);
            System.out.println("Opis bledu: " + out[0]);
        }
        else{
            api.PIK_ZeZmiennej(idSesji, "SCHEDULE.Lista", out);
            System.out.println("Harmonogram: \n" + out[0]);
        }
        separator();
    }
        
    void PIK_UmowaKR1() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        
        String Modulo = wczytajZmienna("oModKl");
        String NRBP = wczytajZmienna("oNRB");
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej (idSesji, "KR_SymbolProduktu", defAPI.Z_BUFOR, "KN28");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_WalutaKredytu", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_TypTransakcjiBIK", defAPI.Z_BUFOR, "02");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_TypEkspozycjiKred", defAPI.Z_BUFOR, "04");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_CelFinansowania", defAPI.Z_BUFOR, "05");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_DataRejestracji", defAPI.Z_DATA, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej (idSesji, "KR_DataWyplaty", defAPI.Z_DATA, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej (idSesji, "KR_DataKonca", defAPI.Z_DATA, DataKsiegowaDefP1P1MP1R);//DataKsiegowaDefP1P1R);
        wynik = api.PIK_DoZmiennej (idSesji, "KR_NumerUmowy", defAPI.Z_BUFOR, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Operator", defAPI.Z_BUFOR, "INTERCOMP");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Modulo", defAPI.Z_RACH, Modulo);
        wynik = api.PIK_DoZmiennej (idSesji, "KR_KwotaBrutto", defAPI.Z_KWOTA, "20000");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_TypHarmonogramu", defAPI.Z_BUFOR, "01"); 
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Zawarcia", defAPI.Z_DATA, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej (idSesji, "KR_KoniecMiesiaca", defAPI.Z_RACH, "0");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_RachunekWyplaty", defAPI.Z_BUFOR, NRBP);
        wynik = api.PIK_DoZmiennej (idSesji, "KR_WlascicielRachWypl", defAPI.Z_MEMO, "");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Ubz_Kod", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Ubz_KwotaSkladki", defAPI.Z_KWOTA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Ubz_Stawka", defAPI.Z_KWOTA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Ubz_TrybPobrania", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Oprc_Typ", defAPI.Z_RACH, "2");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Oprc_Tabela", defAPI.Z_BUFOR, "10");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Oprc_Mnoznik", defAPI.Z_KWOTA, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Oprc_Podstawa", defAPI.Z_KWOTA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_DataPierwszejRaty", defAPI.Z_DATA, DataKsiegowaDefP1P1M);
        wynik = api.PIK_DoZmiennej (idSesji, "KR_LiczbaRat", defAPI.Z_RACH, "13");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_JednostkaOkresu", defAPI.Z_RACH, "2");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_IdPosrednika", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Prw", defAPI.Z_MEMO, "");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Prw_KwotaProwizji1", defAPI.Z_KWOTA, "0");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_KredytKonsumencki", defAPI.Z_BUFOR, "KK2");
        
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Prw_KwotaProwizji2", defAPI.Z_KWOTA, "0");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Prw_KwotaProwizji3", defAPI.Z_KWOTA, "0");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_Jednostka", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_ZaokraglenieRat", defAPI.Z_BUFOR, "2"); 
        wynik = api.PIK_DoZmiennej (idSesji, "KR_SposobWyplaty", defAPI.Z_RACH, "2");
        wynik = api.PIK_DoZmiennej (idSesji, "KR_IdUmowy", defAPI.Z_RACH, ""); 
        wynik = api.PIK_DoZmiennej (idSesji, "KR_NumerRef", defAPI.Z_BUFOR, ""); 
        wynik = api.PIK_DoZmiennej (idSesji, "KR_RachunekSplat", defAPI.Z_BUFOR, ""); 
        wynik = api.PIK_DoZmiennej (idSesji, "KR_BladNr", defAPI.Z_RACH, ""); 
        wynik = api.PIK_DoZmiennej (idSesji, "KR_BladOpis", defAPI.Z_MEMO, ""); 
                
        wynik = api.PIK_UmowaKR(idSesji, api.KR_A_ZALOZENIE);
        System.out.println("PIK_UmowaKR(1) : " + wynik);
        String out [] = new String[10];
        
        if (wynik == 0){
            System.out.println("PIK_UmowaKR(13)  : " + wynik);
            wyswietlBlad(1);
            api.PIK_ZeZmiennej(idSesji, "KR_BladNr", out);
            System.out.println("Numer bledu: " + out[0]);
            api.PIK_ZeZmiennej(idSesji, "KR_BladOpis", out);
            System.out.println("Opis bledu: " + out[0]);
        }
        else{
            api.PIK_ZeZmiennej(idSesji, "KR_IdUmowy", out);
            System.out.println("Identyfikator transakcji: " + out[0]);
            zapiszZmienna("IDKred", out[0]);
            api.PIK_ZeZmiennej(idSesji, "KR_NumerRef", out);
            System.out.println("Numer referencyjny umowy: " + out[0]);
            api.PIK_ZeZmiennej(idSesji, "KR_RachunekSplat", out);
            System.out.println("Numer rachunku spłat: " + out[0]);
            zapiszZmienna("oNRBKr", out[0]);
        }
        separator();
    }
    
    void PIK_WykonajProcedure_PobierzInfo_WKR() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        
        int wynik = 0;
        String ID = "";
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        ID = wczytajZmienna("IDAnDefKred");
        
        wynik = api.PIK_DoZmiennej(idSesji, "TypOperacji",defAPI.Z_BUFOR, "WKR");
        wynik = api.PIK_DoZmiennej(idSesji, "RACH_KR.ID_AN_DEF",defAPI.Z_BUFOR, ID);
        wynik = api.PIK_DoZmiennej(idSesji, "RACH_KR.DODATK_POWIAZ",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "RACH_KR.Lista",defAPI.Z_MEMO, "");
        
        wynik = api.PIK_WykonajProcedure(idSesji, "PobierzInfo");
        System.out.println("PIK_WykonajProcedure PobierzInfo_WKR : " + wynik);
        String[] lista = new String[1];
        
        if(wynik == WYNIK_OK){
            wynik = api.PIK_ZeZmiennej( idSesji, "RACH_KR.Lista", lista);
            System.out.println("RACH_KR.Lista : " + lista[0]);
        }else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    void PIK_WpiszPaczke() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        /*Zewnętrzny*/
        int wynik = 0, poprawnych = 0;
        String rachNadawcy = "", dataKomunikatu = "";

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();

        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);

        rachNadawcy = wczytajZmienna("oNRB");

        dataKomunikatu = dateFormat.format(date);
        
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Strumien, defAPI.Z_KWOTA , "20");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypZlecenia, defAPI.Z_RACH , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KodDokumentu, defAPI.Z_BUFOR , "PR");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KorespondentNad, defAPI.Z_RACH , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekNadawcy, defAPI.Z_BUFOR , rachNadawcy);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaNadawcy, defAPI.Z_MEMO , "PIK DK Zew");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_AdresatFinalny, defAPI.Z_RACH , "21300004");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekAdresata, defAPI.Z_BUFOR , "63213000042001019529770001");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaAdresata, defAPI.Z_MEMO , "KLIENT testowy");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Kwota, defAPI.Z_KWOTA , "123.89");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Waluta, defAPI.Z_BUFOR , "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_DataKomunikatu, defAPI.Z_DATA , dataKomunikatu);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_OpisDodatkowy, defAPI.Z_MEMO , "Przelew \u015Brodk\u00F3w 1\nPrzelew \u015Brodk\u00F3w 2\nPrzelew \u015Brodk\u00F3w 3");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Rozliczenie, defAPI.Z_RACH , "0");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Inf_Miedzybank, defAPI.Z_BUFOR , "1");
        //PSD
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzyPSD", defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.DataPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzasPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.Kurs", defAPI.Z_BUFOR , "0.0");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.ListaOplat", defAPI.Z_BUFOR , "<Dysp.Oplaty></Dysp.Oplaty>");
        
        wynik = api.PIK_DodajKomunikat(idSesji, defAPI.ELX_RK_UZNANIE);
        System.out.println("PIK_DodajKomunikat  : " + wynik); 
        
        if(wynik == WYNIK_OK)            
        {
            poprawnych++;
        }
        
        /*US*/
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);

        rachNadawcy = wczytajZmienna("oNRB");
        dataKomunikatu = dateFormat.format(date);
        
        String NRB_US = NRB_US_NIP();
        
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Strumien, defAPI.Z_KWOTA , "20");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_ID, defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypZlecenia, defAPI.Z_RACH , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KodDokumentu, defAPI.Z_BUFOR , "71");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KorespondentNad, defAPI.Z_RACH , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekNadawcy, defAPI.Z_BUFOR , rachNadawcy);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaNadawcy, defAPI.Z_MEMO , "PIK DK US");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_AdresatFinalny, defAPI.Z_RACH , "10100071");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekAdresata, defAPI.Z_BUFOR , NRB_US);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaAdresata, defAPI.Z_MEMO , "KLIENT testowy");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Kwota, defAPI.Z_KWOTA , "123.89");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Waluta, defAPI.Z_BUFOR , "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_DataKomunikatu, defAPI.Z_DATA , dataKomunikatu);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_OpisDodatkowy, defAPI.Z_MEMO , "Przelew \u015Brodk\u00F3w 1\nPrzelew \u015Brodk\u00F3w 2\nPrzelew \u015Brodk\u00F3w 3");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Rozliczenie, defAPI.Z_RACH , "0");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Inf_Miedzybank, defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypIdentyfik, defAPI.Z_BUFOR , "N");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Identyfikator, defAPI.Z_BUFOR , "1111111111");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Okres, defAPI.Z_BUFOR , "20R");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Symbol, defAPI.Z_BUFOR , "PIT");
        //PSD
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzyPSD", defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.DataPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzasPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.Kurs", defAPI.Z_BUFOR , "0.0");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.ListaOplat", defAPI.Z_BUFOR , "<Dysp.Oplaty></Dysp.Oplaty>");
        
        wynik = api.PIK_DodajKomunikat(idSesji, defAPI.ELX_RK_PLATNOSC_DO_US);
        System.out.println("PIK_DodajKomunikat  : " + wynik); 
        
        if(wynik == WYNIK_OK)            
        {
            poprawnych++;
        }
        
        /*ZUS*/
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);

        rachNadawcy = wczytajZmienna("oNRB");

        dataKomunikatu = dateFormat.format(date);
        
        String NRB_ZUS = NRB_ZUS_NIP();
        
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Strumien, defAPI.Z_KWOTA , "20");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypZlecenia, defAPI.Z_RACH , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KodDokumentu, defAPI.Z_BUFOR , "PR");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KorespondentNad, defAPI.Z_RACH , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekNadawcy, defAPI.Z_BUFOR , rachNadawcy);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaNadawcy, defAPI.Z_MEMO , "PIK DK ZUS");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_AdresatFinalny, defAPI.Z_RACH , "60000002");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekAdresata, defAPI.Z_BUFOR , NRB_ZUS);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaAdresata, defAPI.Z_MEMO , "KLIENT testowy");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Kwota, defAPI.Z_KWOTA , "123.89");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Waluta, defAPI.Z_BUFOR , "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_DataKomunikatu, defAPI.Z_DATA , dataKomunikatu);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_OpisDodatkowy, defAPI.Z_MEMO , "Przelew \u015Brodk\u00F3w 1\nPrzelew \u015Brodk\u00F3w 2\nPrzelew \u015Brodk\u00F3w 3");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Rozliczenie, defAPI.Z_RACH , "0");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Inf_Miedzybank, defAPI.Z_BUFOR , "1");
        //PSD
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzyPSD", defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.DataPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzasPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.Kurs", defAPI.Z_BUFOR , "0.0");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.ListaOplat", defAPI.Z_BUFOR , "<Dysp.Oplaty></Dysp.Oplaty>");
        
        wynik = api.PIK_DodajKomunikat(idSesji, defAPI.ELX_RK_UZNANIE);
        System.out.println("PIK_DodajKomunikat  : " + wynik); 
        
        if(wynik == WYNIK_OK)            
        {
            poprawnych++;
        }
        
        String[] wartosc = new String[1];
        
        if(poprawnych > 0)            
        {
            wynik = api.PIK_UsunWszystkieZmienne (idSesji);
            System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
            
            wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Strumien, defAPI.Z_KWOTA , "20");
            
            wynik = api.PIK_WpiszPaczke(idSesji, "WWW", wartosc);
            System.out.println("PIK_WpiszPaczke  : " + wynik);
            
            if(wynik == WYNIK_OK)            
            {
                System.out.println("\tPaczka_ID: " + wartosc[0] );
            }
            else
            {
                System.out.println("wynik != WYNIK_OK");
                wyswietlBlad(1);            
            }
        }
        else
        {
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(1);            
        }
        separator();
        try{
            Thread.sleep(LongSleep);
        }catch (InterruptedException e){
        }
    }
    
    void PIK_WykonajZlecenie2_RozliczSZ() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String currDate = "", NRB = "", BN = "", NRBo = "", BNo = "", nr_auto = "", kwota = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        currDate = dateFormat.format(date);
        NRB = wczytajZmienna("oNRB").replace(" ", "");
        BN = NRB.substring(2,10);
        NRBo = "30109010987651004978704473";
        BNo = NRBo.substring(2,10);
        nr_auto = wczytajZmienna("oNrZewAutoRozl");
        kwota = "321.15";
        
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypKomunikatu", defAPI.Z_RACH, "111");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Status", defAPI.Z_RACH, "5.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Kwota", defAPI.Z_KWOTA, kwota);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataKomunikatu", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataRealizacji", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankNadawca", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankAdresat", defAPI.Z_RACH, BNo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KorespondentNad", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.AdresatFinalny", defAPI.Z_RACH, BNo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekNadawcy", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekAdresata", defAPI.Z_BUFOR, NRBo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaNadawcy", defAPI.Z_MEMO, "PIK WZ");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaAdresata", defAPI.Z_MEMO, "KLIENT testowy");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KodDokumentu", defAPI.Z_MEMO, "51");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Inf.Międzybank.", defAPI.Z_MEMO, "\nSZ99"+ nr_auto + "\n \n \n");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDKomunikatu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypZlecenia", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Strumień", defAPI.Z_BUFOR, "20"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerJednostki", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypObsługi", defAPI.Z_BUFOR, "1.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypDrogi", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.OpisDodatkowy", defAPI.Z_MEMO, "Rozliczenie Autoryzacji WZ");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR, "WWW");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataDokumentu", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerDokumentu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Rozliczenie", defAPI.Z_RACH, "0");
        
        wynik = api.PIK_WykonajZlecenie(idSesji, defAPI.ELX_RK_UZNANIE, "WWW" , "0");
        
        System.out.println("PIK_WykonajZlecenie  : " + wynik);
        if (wynik == 0){
            System.out.println("PIK_WykonajZlecenie  : " + wynik);
            wyswietlBlad(1);
        }
        separator();
        try{
            Thread.sleep(ShortSleep);
        }catch (InterruptedException e){
        }
    }
    
    void PIK_WpiszKomunikat2_RozliczSZ() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String currDate = "", NRB = "", BN = "", NRBo = "", BNo = "", nr_auto = "", kwota = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        currDate = dateFormat.format(date);
        NRB = wczytajZmienna("oNRB").replace(" ", "");
        BN = NRB.substring(2,10);
        NRBo = "30109010987651004978704473";
        BNo = NRBo.substring(2,10);
        nr_auto = wczytajZmienna("oNrZewAutoRozl");
        kwota = "321.15";
        
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypKomunikatu", defAPI.Z_RACH, "111");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Status", defAPI.Z_RACH, "5.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Kwota", defAPI.Z_KWOTA, kwota);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataKomunikatu", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataRealizacji", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankNadawca", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankAdresat", defAPI.Z_RACH, BNo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KorespondentNad", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.AdresatFinalny", defAPI.Z_RACH, BNo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekNadawcy", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekAdresata", defAPI.Z_BUFOR, NRBo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaNadawcy", defAPI.Z_MEMO, "PIK WZ");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaAdresata", defAPI.Z_MEMO, "KLIENT testowy");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KodDokumentu", defAPI.Z_MEMO, "51");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Inf.Międzybank.", defAPI.Z_MEMO, "\nSZ99"+ nr_auto + "\n \n \n");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDKomunikatu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypZlecenia", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Strumień", defAPI.Z_BUFOR, "20"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerJednostki", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypObsługi", defAPI.Z_BUFOR, "1.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypDrogi", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.OpisDodatkowy", defAPI.Z_MEMO, "Rozliczenie Autoryzacji WK");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR, "WWW"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataDokumentu", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerDokumentu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Rozliczenie", defAPI.Z_RACH, "0");
        
        wynik = api.PIK_WpiszKomunikat(idSesji, defAPI.ELX_RK_UZNANIE, "WWW");
        
        System.out.println("PIK_WpiszKomunikat  : " + wynik);
        if (wynik == 0){
            System.out.println("PIK_WpiszKomunikat zlecenie  : " + wynik);
            wyswietlBlad(1);
        }
        separator();
        try{
            Thread.sleep(LongSleep);
        }catch (InterruptedException e){
        }
    }
    
    void PIK_WykonajZlecenie_ZalozDepozyt() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        
        int wynik;
        String currDate = "", NRB = "", BN = "", MKU = "", kwota = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        currDate = dateFormat.format(date);
        NRB = wczytajZmienna("oNRB").replace(" ", "");
        MKU = wczytajZmienna("iModZS") + "-" + wczytajZmienna("iKonZS") + " " + wczytajZmienna("iUwaZS");
        
        /*NRB="42 8642 0002 3001 8401 0719 0001".replace(" ","");
        MKU="84010719-27414-101 01";
        */
        
        BN = NRB.substring(2,10);
        kwota = "10000.00";
        /*
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypKomunikatu", defAPI.Z_RACH, "111");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Status", defAPI.Z_RACH, "5.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataRealizacji", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankNadawca", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankAdresat", defAPI.Z_RACH, BNo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDKomunikatu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerJednostki", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypObsługi", defAPI.Z_BUFOR, "1.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypDrogi", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataDokumentu", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerDokumentu", defAPI.Z_BUFOR, "");
        */
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Strumień", defAPI.Z_BUFOR, "20"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.ID", defAPI.Z_BUFOR, ""); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypZlecenia", defAPI.Z_RACH, "5");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KorespondentNad", defAPI.Z_RACH, "19300018");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekNadawcy", defAPI.Z_BUFOR, MKU);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaNadawcy", defAPI.Z_MEMO, "PIK Lokata");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.AdresatFinalny", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekAdresata", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaAdresata", defAPI.Z_MEMO, "PIK Lokata");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Kwota", defAPI.Z_KWOTA, kwota);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataKomunikatu", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.OpisDodatkowy", defAPI.Z_MEMO, "Założenie depozytu");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Rozliczenie", defAPI.Z_RACH, "0");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Inf.Międzybank.", defAPI.Z_MEMO, "\n \n \n \n \n7  DNNSTOZ  Z");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KodDokumentu", defAPI.Z_BUFOR, "PR");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR, "WWW"); 
        
        //wynik = api.PIK_WpiszKomunikat(idSesji, defAPI.ELX_RK_UZNANIE, "WWW");
        wynik = api.PIK_WykonajZlecenie(idSesji, defAPI.ELX_RK_UZNANIE, "WWW", "");
        
        System.out.println("PIK_WykonajZlecenie  : " + wynik);
        if (wynik == 0){
            System.out.println("PIK_WykonajZlecenie  : " + wynik);
            wyswietlBlad(1);
        }
        separator();
        try{
            Thread.sleep(LongSleep);
        }catch (InterruptedException e){
        }
    }
    
    void PIK_DajRachunekBiezacy5() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String ID = "";
        
        ID = wczytajZmienna("oIdKlF");
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        
        api.PIK_DoZmiennej(idSesji, "RB_NRB", defAPI.Z_BUFOR, "");
        api.PIK_DoZmiennej(idSesji, "RB_ObslugaMPP", defAPI.Z_BUFOR, "");
        api.PIK_DoZmiennej(idSesji, "RB_RachunekVAT", defAPI.Z_BUFOR, "");
        
        wynik = api.PIK_DajRachunekBiezacy(idSesji, defAPI.RB_A_LISTA_AKTYWNYCH, ID);
        System.out.println("PIK_DajRachunekBiezacy  : " + wynik); 
        
        if(wynik == WYNIK_OK){
            System.out.println("PIK_LiczbaRekordow  : " +  api.PIK_LiczbaRekordow(idSesji));

            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK)
            {
                String [] out = new String[1];
                int i = 0;
                do
                {
                    i += 1;
                    System.out.println(i + " : ");
                    System.out.println();
                    
                    api.PIK_ZeZmiennej(idSesji, "RB_NRB", out);
                    System.out.println("RB_NRB : " + out[0]);

                    api.PIK_ZeZmiennej(idSesji, "RB_ObslugaMPP", out);
                    System.out.println("RB_ObslugaMPP : " + out[0]);
            
                    if(out[0].compareTo("Tak") == 0){
                        api.PIK_ZeZmiennej(idSesji, "RB_RachunekVAT", out);
                        System.out.println("RB_RachunekVAT : " + out[0]);
                        zapiszZmienna("oNRBVat", out[0]);
                    }
                    
            
                    System.out.println();
                }while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
        
           
            }
        }
        else{
            wyswietlBlad(0);
        }
        
        separator();
        
    }
    
    void PIK_WpiszKomunikatMPPWewSV() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String currDate = "", NRB = "", BN = "", NRBo = "", BO = "", tmpfkcja = "", tmprozl = "", kwota, kwotaVAT = "", nr = "", tmp = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        currDate = dateFormat.format(date);
        
        nr = wczytajZmienna("iNrKolejny");
        NRB = NRBSrodki;
        BN = NRB.substring(2,10);
        NRBo = wczytajZmienna("oNRB").replace(" ","");
        BO = NRBo.substring(2,10);
        
        kwota="500.00";
        kwotaVAT="500.00";
                
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypKomunikatu", defAPI.Z_BUFOR, "111");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Status", defAPI.Z_RACH, "5.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Kwota", defAPI.Z_KWOTA, kwota);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KwotaVAT", defAPI.Z_KWOTA, kwotaVAT);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDC", defAPI.Z_BUFOR, "NIP Klienta");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.INV", defAPI.Z_BUFOR, "FV/MPP/Auto/" + (currDate.replace(".","")).substring(2) + "/" + nr);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TXT", defAPI.Z_BUFOR, "Przelew MPP API Auto");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataKomunikatu", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataRealizacji", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankNadawca", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankAdresat", defAPI.Z_RACH, BO);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KorespondentNad", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.AdresatFinalny", defAPI.Z_RACH, BO);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekNadawcy", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekAdresata", defAPI.Z_BUFOR, NRBo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaNadawcy", defAPI.Z_MEMO, "PIK WK " + currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaAdresata", defAPI.Z_MEMO, "KLIENT testowy");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KodDokumentu", defAPI.Z_MEMO, "53");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Inf.Międzybank.", defAPI.Z_MEMO, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDKomunikatu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypZlecenia", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Strumień", defAPI.Z_BUFOR, "20"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerJednostki", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypObsługi", defAPI.Z_BUFOR, "1.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypDrogi", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.OpisDodatkowy", defAPI.Z_MEMO, "Test Corba " + currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR, "WWW"); //WWW WEW
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataDokumentu", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerDokumentu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Rozliczenie", defAPI.Z_RACH, "0");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.ID", defAPI.Z_RACH, "");
        
        wynik = api.PIK_WpiszKomunikat(idSesji, defAPI.ELX_RK_UZNANIE_MPP, "WWW");
        System.out.println("PIK_WpiszKomunikat  : " + wynik);
        if (wynik == 0){
            System.out.println("PIK_WpiszKomunikat  : " + wynik);
            wyswietlBlad(0);
        }
        else{
            String [] out = new String[1];
            wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.ID", out);
            System.out.println("ELIXIR.ID = " + out[0]);
            zapiszZmienna("oElixirID", out[0]);
        }
            
        wynik = Integer.parseInt(nr);
        wynik++;
        zapiszZmienna("iNrKolejny", "" + wynik);
        separator();
        //Czekajka
        try{
            Thread.sleep(LongSleep);
        }catch(InterruptedException e){}
    }
    
    void ZasilRBzMPP() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String currDate = "", NRB = "", BN = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        currDate = dateFormat.format(date);
        NRB = wczytajZmienna("oNRB");
        BN = NRB.substring(2,10);

        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypKomunikatu", defAPI.Z_RACH, "111");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Status", defAPI.Z_RACH, "5.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Kwota", defAPI.Z_KWOTA, "5000.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataKomunikatu", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataRealizacji", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankNadawca", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankAdresat", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KorespondentNad", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.AdresatFinalny", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekNadawcy", defAPI.Z_BUFOR, NRBSrodki);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekAdresata", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaNadawcy", defAPI.Z_MEMO, "KLIENT testowy");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaAdresata", defAPI.Z_MEMO, "KLIENT testowy");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KodDokumentu", defAPI.Z_MEMO, "51");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Inf.Międzybank.", defAPI.Z_MEMO, "\n\n\n\n");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDKomunikatu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypZlecenia", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Strumień", defAPI.Z_BUFOR, "20"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerJednostki", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypObsługi", defAPI.Z_BUFOR, "1.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypDrogi", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.OpisDodatkowy", defAPI.Z_MEMO, "Zasilenie rachunku");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR, "WWW"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataDokumentu", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerDokumentu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Rozliczenie", defAPI.Z_RACH, "0");
        
        wynik = api.PIK_WykonajZlecenie(idSesji, defAPI.ELX_RK_UZNANIE, "WWW" , "0");
        
        System.out.println("PIK_WykonajZlecenie  : " + wynik);
        if (wynik == 0){
            System.out.println("PIK_Wykonaj zlecenie  : " + wynik);
            wyswietlBlad(1);
        }
        separator();
        try{
            Thread.sleep(ShortSleep);
        }catch (InterruptedException e){
        }
    }
    
    void PIK_WykonajZlecenieMPPWewKV() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String currDate = "", NRB = "", BN = "", NRBo = "", BO = "", tmpfkcja = "", tmprozl = "", kwota, kwotaVAT = "", nr = "", tmp = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        currDate = dateFormat.format(date);
        
        nr = wczytajZmienna("iNrKolejny");
        NRB = NRBSrodki;
        BN = NRB.substring(2,10);
        NRBo = wczytajZmienna("oNRB").replace(" ","");
        BO = NRBo.substring(2,10);
        
        kwota="4000.00";
        kwotaVAT="1000.00";
                
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypKomunikatu", defAPI.Z_BUFOR, "111");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Status", defAPI.Z_RACH, "5.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Kwota", defAPI.Z_KWOTA, kwota);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KwotaVAT", defAPI.Z_KWOTA, kwotaVAT);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDC", defAPI.Z_BUFOR, "NIP Klienta");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.INV", defAPI.Z_BUFOR, "FV/MPP/Auto/" + (currDate.replace(".","")).substring(2) + "/" + nr);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TXT", defAPI.Z_BUFOR, "Przelew MPP API Auto");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataKomunikatu", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataRealizacji", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankNadawca", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankAdresat", defAPI.Z_RACH, BO);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KorespondentNad", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.AdresatFinalny", defAPI.Z_RACH, BO);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekNadawcy", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekAdresata", defAPI.Z_BUFOR, NRBo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaNadawcy", defAPI.Z_MEMO, "PIK WZ " + currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaAdresata", defAPI.Z_MEMO, "KLIENT testowy");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KodDokumentu", defAPI.Z_MEMO, "53");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Inf.Międzybank.", defAPI.Z_MEMO, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDKomunikatu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypZlecenia", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Strumień", defAPI.Z_BUFOR, "20"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerJednostki", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypObsługi", defAPI.Z_BUFOR, "1.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypDrogi", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.OpisDodatkowy", defAPI.Z_MEMO, "Test Corba " + currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR, "WWW"); //WWW WEW
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataDokumentu", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerDokumentu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Rozliczenie", defAPI.Z_RACH, "0");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.ID", defAPI.Z_RACH, "");
        
        wynik = api.PIK_WykonajZlecenie(idSesji, defAPI.ELX_RK_UZNANIE_MPP, "WWW" , "0");
        System.out.println("PIK_WykonajZlecenie  : " + wynik);
        if (wynik == 0){
            System.out.println("PIK_WykonajZlecenie  : " + wynik);
            wyswietlBlad(0);
        }
        else{
            String [] wartosc = new String[1];
            api.PIK_ZeZmiennej(idSesji, "ELIXIR.IDKomunikatu", wartosc);
            System.out.println("\tELIXIR.IDKomunikatu: " + wartosc[0] );
            zapiszZmienna("oElixirID", wartosc[0]);
        }
        wynik = Integer.parseInt(nr);
        wynik++;
        zapiszZmienna("iNrKolejny", "" + wynik);
        try{
            Thread.sleep(ShortSleep);
        }catch(InterruptedException e){}
        separator();
    }
    
    void PIK_WykonajZlecenieMPPZewKV() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String currDate = "", NRB = "", BN = "", NRBo = "", BO = "", tmpfkcja = "", tmprozl = "", kwota, kwotaVAT = "", nr = "", tmp = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        currDate = dateFormat.format(date);
        
        nr = wczytajZmienna("iNrKolejny");
        NRB = wczytajZmienna("oNRB").replace(" ","");
        BN = NRB.substring(2,10);
        NRBo = "30109010987651004978704473";
        BO = NRBo.substring(2,10);
        
        kwota="12.00";
        kwotaVAT="2.00";
                
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypKomunikatu", defAPI.Z_BUFOR, "111");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Status", defAPI.Z_RACH, "5.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Kwota", defAPI.Z_KWOTA, kwota);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KwotaVAT", defAPI.Z_KWOTA, kwotaVAT);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDC", defAPI.Z_BUFOR, "NIP Klienta");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.INV", defAPI.Z_BUFOR, "FV/MPP/Auto/" + (currDate.replace(".","")).substring(2) + "/" + nr);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TXT", defAPI.Z_BUFOR, "Przelew MPP API Auto");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataKomunikatu", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataRealizacji", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankNadawca", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankAdresat", defAPI.Z_RACH, BO);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KorespondentNad", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.AdresatFinalny", defAPI.Z_RACH, BO);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekNadawcy", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekAdresata", defAPI.Z_BUFOR, NRBo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaNadawcy", defAPI.Z_MEMO, "PIK WZ " + currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaAdresata", defAPI.Z_MEMO, "KLIENT testowy");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KodDokumentu", defAPI.Z_MEMO, "53");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Inf.Międzybank.", defAPI.Z_MEMO, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDKomunikatu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypZlecenia", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Strumień", defAPI.Z_BUFOR, "20"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerJednostki", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypObsługi", defAPI.Z_BUFOR, "1.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypDrogi", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.OpisDodatkowy", defAPI.Z_MEMO, "Test Corba " + currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR, "WWW"); //WWW WEW
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataDokumentu", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerDokumentu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Rozliczenie", defAPI.Z_RACH, "0");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.ID", defAPI.Z_RACH, "");
        
        wynik = api.PIK_WykonajZlecenie(idSesji, defAPI.ELX_RK_UZNANIE_MPP, "WWW" , "0");
        System.out.println("PIK_WykonajZlecenie  : " + wynik);
        if (wynik == 0){
            System.out.println("PIK_WykonajZlecenie  : " + wynik);
            wyswietlBlad(0);
        }
        else{
            String [] wartosc = new String[1];
            api.PIK_ZeZmiennej(idSesji, "ELIXIR.IDKomunikatu", wartosc);
            System.out.println("\tELIXIR.IDKomunikatu: " + wartosc[0] );
            zapiszZmienna("oElixirID", wartosc[0]);
        }
        wynik = Integer.parseInt(nr);
        wynik++;
        zapiszZmienna("iNrKolejny", "" + wynik);
        try{
            Thread.sleep(ShortSleep);
        }catch(InterruptedException e){}
        separator();
    }
    
    void PIK_WpiszKomunikatMPPZewKV() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String currDate = "", NRB = "", BN = "", NRBo = "", BO = "", tmpfkcja = "", tmprozl = "", kwota, kwotaVAT = "", nr = "", tmp = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        currDate = dateFormat.format(date);
        
        nr = wczytajZmienna("iNrKolejny");
        NRB = wczytajZmienna("oNRB").replace(" ","");
        BN = NRB.substring(2,10);
        NRBo = "30109010987651004978704473" ;
        BO = NRBo.substring(2,10);
        
        kwota="24.00";
        kwotaVAT="4.00";
                
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypKomunikatu", defAPI.Z_BUFOR, "111");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Status", defAPI.Z_RACH, "5.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Kwota", defAPI.Z_KWOTA, kwota);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KwotaVAT", defAPI.Z_KWOTA, kwotaVAT);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDC", defAPI.Z_BUFOR, "NIP Klienta");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.INV", defAPI.Z_BUFOR, "FV/MPP/Auto/" + (currDate.replace(".","")).substring(2) + "/" + nr);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TXT", defAPI.Z_BUFOR, "Przelew MPP API Auto");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataKomunikatu", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataRealizacji", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankNadawca", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankAdresat", defAPI.Z_RACH, BO);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KorespondentNad", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.AdresatFinalny", defAPI.Z_RACH, BO);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekNadawcy", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekAdresata", defAPI.Z_BUFOR, NRBo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaNadawcy", defAPI.Z_MEMO, "PIK WK " + currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaAdresata", defAPI.Z_MEMO, "KLIENT testowy");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KodDokumentu", defAPI.Z_MEMO, "53");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Inf.Międzybank.", defAPI.Z_MEMO, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDKomunikatu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypZlecenia", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Strumień", defAPI.Z_BUFOR, "20"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerJednostki", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypObsługi", defAPI.Z_BUFOR, "1.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypDrogi", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.OpisDodatkowy", defAPI.Z_MEMO, "Test Corba " + currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR, "WWW"); //WWW WEW
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataDokumentu", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerDokumentu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Rozliczenie", defAPI.Z_RACH, "2");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.ID", defAPI.Z_RACH, "");
        
        wynik = api.PIK_WpiszKomunikat(idSesji, defAPI.ELX_RK_UZNANIE_MPP, "WWW");
        System.out.println("PIK_WpiszKomunikat  : " + wynik);
        if (wynik == 0){
            System.out.println("PIK_WpiszKomunikat  : " + wynik);
            wyswietlBlad(0);
        }
        else{
            String [] out = new String[1];
            wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.ID", out);
            System.out.println("ELIXIR.ID = " + out[0]);
            zapiszZmienna("oElixirID", out[0]);
        }
            
        wynik = Integer.parseInt(nr);
        wynik++;
        zapiszZmienna("iNrKolejny", "" + wynik);
        separator();
        //Czekajka
        try{
            Thread.sleep(LongSleep);
        }catch(InterruptedException e){}
    }
    
    void PIK_WykonajZlecenieMPPZewSV() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String currDate = "", NRB = "", BN = "", NRBo = "", BO = "", tmpfkcja = "", tmprozl = "", kwota, kwotaVAT = "", nr = "", tmp = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        currDate = dateFormat.format(date);
        
        nr = wczytajZmienna("iNrKolejny");
        NRB = wczytajZmienna("oNRB").replace(" ","");
        BN = NRB.substring(2,10);
        NRBo = "30109010987651004978704473";
        BO = NRBo.substring(2,10);
        
        kwota="5.00";
        kwotaVAT="5.00";
                
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypKomunikatu", defAPI.Z_BUFOR, "111");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Status", defAPI.Z_RACH, "5.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Kwota", defAPI.Z_KWOTA, kwota);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KwotaVAT", defAPI.Z_KWOTA, kwotaVAT);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDC", defAPI.Z_BUFOR, "NIP Klienta");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.INV", defAPI.Z_BUFOR, "FV/MPP/Auto/" + (currDate.replace(".","")).substring(2) + "/" + nr);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TXT", defAPI.Z_BUFOR, "Przelew MPP API Auto");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataKomunikatu", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataRealizacji", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankNadawca", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankAdresat", defAPI.Z_RACH, BO);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KorespondentNad", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.AdresatFinalny", defAPI.Z_RACH, BO);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekNadawcy", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekAdresata", defAPI.Z_BUFOR, NRBo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaNadawcy", defAPI.Z_MEMO, "PIK WZ " + currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaAdresata", defAPI.Z_MEMO, "KLIENT testowy");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KodDokumentu", defAPI.Z_MEMO, "53");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Inf.Międzybank.", defAPI.Z_MEMO, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDKomunikatu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypZlecenia", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Strumień", defAPI.Z_BUFOR, "20"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerJednostki", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypObsługi", defAPI.Z_BUFOR, "1.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypDrogi", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.OpisDodatkowy", defAPI.Z_MEMO, "Test Corba " + currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR, "WWW"); //WWW WEW
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataDokumentu", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerDokumentu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Rozliczenie", defAPI.Z_RACH, "2");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.ID", defAPI.Z_RACH, "");
        
        wynik = api.PIK_WykonajZlecenie(idSesji, defAPI.ELX_RK_UZNANIE_MPP, "WWW" , "0");
        System.out.println("PIK_WykonajZlecenie  : " + wynik);
        if (wynik == 0){
            System.out.println("PIK_WykonajZlecenie  : " + wynik);
            wyswietlBlad(0);
        }
        else{
            String [] wartosc = new String[1];
            api.PIK_ZeZmiennej(idSesji, "ELIXIR.IDKomunikatu", wartosc);
            System.out.println("\tELIXIR.IDKomunikatu: " + wartosc[0] );
            zapiszZmienna("oElixirID", wartosc[0]);
        }
        wynik = Integer.parseInt(nr);
        wynik++;
        zapiszZmienna("iNrKolejny", "" + wynik);
        try{
            Thread.sleep(ShortSleep);
        }catch(InterruptedException e){}
        separator();
    }
    
    void PIK_WpiszKomunikatMPPZewSV() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String currDate = "", NRB = "", BN = "", NRBo = "", BO = "", tmpfkcja = "", tmprozl = "", kwota, kwotaVAT = "", nr = "", tmp = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        currDate = dateFormat.format(date);
        
        nr = wczytajZmienna("iNrKolejny");
        NRB = wczytajZmienna("oNRB").replace(" ","");
        BN = NRB.substring(2,10);
        NRBo = "30109010987651004978704473" ;
        BO = NRBo.substring(2,10);
        
        kwota="7.00";
        kwotaVAT="7.00";
                
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypKomunikatu", defAPI.Z_BUFOR, "111");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Status", defAPI.Z_RACH, "5.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Kwota", defAPI.Z_KWOTA, kwota);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KwotaVAT", defAPI.Z_KWOTA, kwotaVAT);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDC", defAPI.Z_BUFOR, "NIP Klienta");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.INV", defAPI.Z_BUFOR, "FV/MPP/Auto/" + (currDate.replace(".","")).substring(2) + "/" + nr);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TXT", defAPI.Z_BUFOR, "Przelew MPP API Auto");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Waluta", defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataKomunikatu", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataRealizacji", defAPI.Z_DATA, currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankNadawca", defAPI.Z_RACH, BN);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.BankAdresat", defAPI.Z_RACH, BO);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KorespondentNad", defAPI.Z_RACH, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.AdresatFinalny", defAPI.Z_RACH, BO);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekNadawcy", defAPI.Z_BUFOR, NRB);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.R-ekAdresata", defAPI.Z_BUFOR, NRBo);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaNadawcy", defAPI.Z_MEMO, "PIK WK " + currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NazwaAdresata", defAPI.Z_MEMO, "KLIENT testowy");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KodDokumentu", defAPI.Z_MEMO, "53");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Inf.Międzybank.", defAPI.Z_MEMO, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.IDKomunikatu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypZlecenia", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Strumień", defAPI.Z_BUFOR, "20"); 
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerJednostki", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypObsługi", defAPI.Z_BUFOR, "1.00");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.TypDrogi", defAPI.Z_RACH, "1");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.OpisDodatkowy", defAPI.Z_MEMO, "Test Corba " + currDate);
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR, "WWW"); //WWW WEW
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.DataDokumentu", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.NumerDokumentu", defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.Rozliczenie", defAPI.Z_RACH, "0");
        wynik = api.PIK_DoZmiennej (idSesji, "ELIXIR.ID", defAPI.Z_RACH, "");
        
        wynik = api.PIK_WpiszKomunikat(idSesji, defAPI.ELX_RK_UZNANIE_MPP, "WWW");
        System.out.println("PIK_WpiszKomunikat  : " + wynik);
        if (wynik == 0){
            System.out.println("PIK_WpiszKomunikat  : " + wynik);
            wyswietlBlad(0);
        }
        else{
            String [] out = new String[1];
            wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.ID", out);
            System.out.println("ELIXIR.ID = " + out[0]);
            zapiszZmienna("oElixirID", out[0]);
        }
            
        wynik = Integer.parseInt(nr);
        wynik++;
        zapiszZmienna("iNrKolejny", "" + wynik);
        separator();
        //Czekajka
        try{
            Thread.sleep(LongSleep);
        }catch(InterruptedException e){}
    }
    
    void PIK_WykonajZlecenieMPPUS() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String rachNadawcy = "", dataKomunikatu = "";

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();

        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);

        rachNadawcy = wczytajZmienna("oNRB");
        dataKomunikatu = dateFormat.format(date);
        
        String NRB_US = NRB_US_NIP();
        
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Strumien, defAPI.Z_KWOTA , "20");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_ID, defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypZlecenia, defAPI.Z_RACH , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KodDokumentu, defAPI.Z_BUFOR , "71");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KorespondentNad, defAPI.Z_RACH , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekNadawcy, defAPI.Z_BUFOR , rachNadawcy);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaNadawcy, defAPI.Z_MEMO , "PIK WZ");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_AdresatFinalny, defAPI.Z_RACH , "10100071");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekAdresata, defAPI.Z_BUFOR , NRB_US);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaAdresata, defAPI.Z_MEMO , "Drugi US Rzeszów");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Kwota, defAPI.Z_KWOTA , "5.55");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Waluta, defAPI.Z_BUFOR , "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_DataKomunikatu, defAPI.Z_DATA , dataKomunikatu);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_OpisDodatkowy, defAPI.Z_MEMO , "Przelew \u015Brodk\u00F3w 1\nPrzelew \u015Brodk\u00F3w 2\nPrzelew \u015Brodk\u00F3w 3");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Rozliczenie, defAPI.Z_RACH , "0");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Inf_Miedzybank, defAPI.Z_MEMO , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypIdentyfik, defAPI.Z_BUFOR , "N");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Identyfikator, defAPI.Z_BUFOR , "1111111111");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Okres, defAPI.Z_BUFOR , "20R");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Symbol, defAPI.Z_BUFOR , "PIT");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR , "WWW");
        //PSD
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzyPSD", defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.DataPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzasPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.Kurs", defAPI.Z_BUFOR , "0.0");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.ListaOplat", defAPI.Z_BUFOR , "<Dysp.Oplaty></Dysp.Oplaty>");
        
        wynik = api.PIK_WykonajZlecenie(idSesji, defAPI.ELX_RK_PLATNOSC_DO_US, "WWW" , "0");
        System.out.println("PIK_WykonajZlecenie  : " + wynik);            

        if(wynik == WYNIK_OK)            
        {
            String [] wartosc = new String[1];
            api.PIK_ZeZmiennej(idSesji, "ELIXIR.IDKomunikatu", wartosc);
            System.out.println("\tELIXIR.IDKomunikatu: " + wartosc[0] );
            zapiszZmienna("oElixirID", wartosc[0]);
        }
        else
        {
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(1);            
        }
        try{
            Thread.sleep(ShortSleep);
        }catch(InterruptedException e){}
        separator();
        
    }
    
    void PIK_WpiszKomunikatMPPUS() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String rachNadawcy = "", dataKomunikatu = "";

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();

        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);

        rachNadawcy = wczytajZmienna("oNRB");
        dataKomunikatu = dateFormat.format(date);
        
        String NRB_US = NRB_US_NIP();
        
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Strumien, defAPI.Z_KWOTA , "20");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_ID, defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypZlecenia, defAPI.Z_RACH , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KodDokumentu, defAPI.Z_BUFOR , "71");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_KorespondentNad, defAPI.Z_RACH , "");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekNadawcy, defAPI.Z_BUFOR , rachNadawcy);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaNadawcy, defAPI.Z_MEMO , "PIK WK");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_AdresatFinalny, defAPI.Z_RACH , "10100071");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_R_ekAdresata, defAPI.Z_BUFOR , NRB_US);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_NazwaAdresata, defAPI.Z_MEMO , "Drugi US Rzeszów");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Kwota, defAPI.Z_KWOTA , "7.77");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Waluta, defAPI.Z_BUFOR , "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_DataKomunikatu, defAPI.Z_DATA , dataKomunikatu);
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_OpisDodatkowy, defAPI.Z_MEMO , "Przelew \u015Brodk\u00F3w 1\nPrzelew \u015Brodk\u00F3w 2\nPrzelew \u015Brodk\u00F3w 3");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Rozliczenie, defAPI.Z_RACH , "0");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Inf_Miedzybank, defAPI.Z_MEMO , "1");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_TypIdentyfik, defAPI.Z_BUFOR , "N");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Identyfikator, defAPI.Z_BUFOR , "1111111111");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Okres, defAPI.Z_BUFOR , "20R");
        wynik = api.PIK_DoZmiennej(idSesji, defAPI.ELIXIR_Symbol, defAPI.Z_BUFOR , "PIT");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.KanalWejscia", defAPI.Z_BUFOR , "WWW");
        //PSD
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzyPSD", defAPI.Z_BUFOR , "1");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.DataPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.CzasPrzekazania", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "PSD.Kurs", defAPI.Z_BUFOR , "0.0");
        wynik = api.PIK_DoZmiennej(idSesji, "Dysp.ListaOplat", defAPI.Z_BUFOR , "<Dysp.Oplaty></Dysp.Oplaty>");
        
        wynik = api.PIK_WpiszKomunikat(idSesji, defAPI.ELX_RK_PLATNOSC_DO_US, "WWW");
        System.out.println("PIK_WpiszKomunikat  : " + wynik);            

        String[] wartosc = new String[1];
        
        if(wynik == WYNIK_OK)            
        {
            api.PIK_ZeZmiennej(idSesji, defAPI.ELIXIR_ID, wartosc);
            System.out.println("\tELIXIR_ID: " + wartosc[0] );
            zapiszZmienna("oElixirID", wartosc[0]);
        }
        else
        {
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(1);            
        }
        separator();
        //Czekajka
        try{
            Thread.sleep(LongSleep);
        }catch(InterruptedException e){}
    }
    
    void PIK_DajDekrety() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        
        String modulo = "", konto = "", uwaga = "", kontoPom = "", data = "";
        String [] saldo1 = new String[1];
        String [] saldo2 = new String[1];
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        PIK_Rachunki5_MPP(); //odczytanie modulo-konto uwaga z NRB
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        modulo = wczytajZmienna("iModZS").replace(" ","");
        konto = wczytajZmienna("iKonZS").replace(" ","");
        uwaga = wczytajZmienna("iUwaZS").replace(" ","");
        
        //OPER
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_DATA_OPERACJI", defAPI.Z_DATA, "");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_DATA_WALUTY", defAPI.Z_DATA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_ZLECENIODAWCA", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_BENEFICJENT", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_SWIFT_ZLECE", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_SWIFT_BENEF", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_NR_RACH_ZLEC", defAPI.Z_RACH,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_NR_RACH_BENEF", defAPI.Z_RACH,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_TYTUL", defAPI.Z_MEMO,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_INSTRUKCJE", defAPI.Z_MEMO,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_WALUTA", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KURS_1", defAPI.Z_KWOTA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KURS_2", defAPI.Z_KWOTA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KWOTA", defAPI.Z_KWOTA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_NR_RACH_WN", defAPI.Z_RACH,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_NR_RACH_MA", defAPI.Z_RACH,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_TYP_OPERACJI", defAPI.Z_RACH,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_STATUS", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_DATA_WPISU", defAPI.Z_DATA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_CZAS_WPISU", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KTO_WPISAL", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_DATA_ZATW", defAPI.Z_DATA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_CZAS_ZATW", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KTO_ZATW", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KURS_RACH_WN", defAPI.Z_KWOTA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KURS_RACH_MA", defAPI.Z_KWOTA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_PROWIZJA", defAPI.Z_KWOTA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KWOTA2", defAPI.Z_KWOTA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_RACH_PROWIZJI", defAPI.Z_RACH,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_NUMER_KARTY", defAPI.Z_RACH,"");
        //OPER MPP
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_NR_RACH_VAT", defAPI.Z_RACH,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KWOTA_VAT", defAPI.Z_KWOTA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_KWOTA_VAT2", defAPI.Z_KWOTA,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_IDC", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_INV", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_IDP", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "OPER_TXT", defAPI.Z_BUFOR,"");
        //Elixir MPP
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.KwotaVAT", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.IDC", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.INV", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.IDP", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.TXT", defAPI.Z_BUFOR,"");
        wynik = api.PIK_DoZmiennej(idSesji, "ELIXIR.KodDokumentu", defAPI.Z_BUFOR,"");
        
        wynik = api.PIK_DajDekrety(idSesji, modulo, konto, uwaga, kontoPom, DataKsiegowaDef, saldo1, saldo2);
        
        System.out.println("PIK_DajDekrety  : " + wynik);

        if(wynik == WYNIK_OK)
        {
            String [] out = new String[1];
            
            System.out.println("PIK_LiczbaRekordow  : " +  api.PIK_LiczbaRekordow(idSesji));

            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK)
            {
                int i = 0;
                do
                {
                    i += 1;
                    System.out.println(i + " : ");
                    System.out.println();
                    
                    wynik = api.PIK_ZeZmiennej(idSesji, "OPER_DATA_OPERACJI", out);
                    //if(out[0].length() > 0){
                        System.out.println("OPER_DATA_OPERACJI:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_DATA_WALUTY", out);
                        System.out.println("OPER_DATA_WALUTY:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_ZLECENIODAWCA", out);
                        System.out.println("OPER_ZLECENIODAWCA:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_BENEFICJENT", out);
                        System.out.println("OPER_BENEFICJENT:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_SWIFT_ZLECE", out);
                        System.out.println("OPER_SWIFT_ZLECE:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_SWIFT_BENEF", out);
                        System.out.println("OPER_SWIFT_BENEF:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_NR_RACH_ZLEC", out);
                        System.out.println("OPER_NR_RACH_ZLEC:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_NR_RACH_BENEF", out);
                        System.out.println("OPER_NR_RACH_BENEF:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_TYTUL", out);
                        System.out.println("OPER_TYTUL:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_INSTRUKCJE", out);
                        System.out.println("OPER_INSTRUKCJE:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_WALUTA", out);
                        System.out.println("OPER_WALUTA:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KURS_1", out);
                        System.out.println("OPER_KURS_1:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KURS_2", out);
                        System.out.println("OPER_KURS_2:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KWOTA", out);
                        System.out.println("OPER_KWOTA:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_NR_RACH_WN", out);
                        System.out.println("OPER_NR_RACH_WN:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_NR_RACH_MA", out);
                        System.out.println("OPER_NR_RACH_MA:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_TYP_OPERACJI", out);
                        System.out.println("OPER_TYP_OPERACJI:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_STATUS", out);
                        System.out.println("OPER_STATUS:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_DATA_WPISU", out);
                        System.out.println("OPER_DATA_WPISU:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_CZAS_WPISU", out);
                        System.out.println("OPER_CZAS_WPISU:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KTO_WPISAL", out);
                        System.out.println("OPER_KTO_WPISAL:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_DATA_ZATW", out);
                        System.out.println("OPER_DATA_ZATW:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_CZAS_ZATW", out);
                        System.out.println("OPER_CZAS_ZATW:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KTO_ZATW", out);
                        System.out.println("OPER_KTO_ZATW:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KURS_RACH_WN", out);
                        System.out.println("OPER_KURS_RACH_WN:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KURS_RACH_MA", out);
                        System.out.println("OPER_KURS_RACH_MA:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_PROWIZJA", out);
                        System.out.println("OPER_PROWIZJA:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KWOTA2", out);
                        System.out.println("OPER_KWOTA2:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_RACH_PROWIZJI", out);
                        System.out.println("OPER_RACH_PROWIZJI:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_NUMER_KARTY", out);
                        System.out.println("OPER_NUMER_KARTY:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_NR_RACH_VAT", out);
                        System.out.println("OPER_NR_RACH_VAT:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KWOTA_VAT", out);
                        System.out.println("OPER_KWOTA_VAT:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_KWOTA_VAT2", out);
                        System.out.println("OPER_KWOTA_VAT2:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_IDC", out);
                        System.out.println("OPER_IDC:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_INV", out);
                        System.out.println("OPER_INV:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_IDP", out);
                        System.out.println("OPER_IDP:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "OPER_TXT", out);
                        System.out.println("OPER_TXT:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.KwotaVAT", out);
                        System.out.println("ELIXIR.KwotaVAT:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.IDC", out);
                        System.out.println("ELIXIR.IDC:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.INV", out);
                        System.out.println("ELIXIR.INV:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.IDP", out);
                        System.out.println("ELIXIR.IDP:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.TXT", out);
                        System.out.println("ELIXIR.TXT:\t" + out[0]);
                        wynik = api.PIK_ZeZmiennej(idSesji, "ELIXIR.KodDokumentu", out);
                        System.out.println("ELIXIR.KodDokumentu:\t" + out[0]);
                    //}
                    System.out.println();
                } while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }
        else
        {
            System.out.println("wynik != WYNIK_OK");
            wyswietlBlad(1);
        }
       
        separator();
        
    }
    
    String PIK_Rachunki5_MPP() throws FileNotFoundException, IOException{
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int wynik = 0;
        String NRB = "";
        
        NRB = wczytajZmienna("oNRB");
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne: " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.NRB", defAPI.Z_BUFOR , NRB);
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.Modulo", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.Konto", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.Uwaga", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.ID", defAPI.Z_BUFOR , "");
        
        wynik = api.PIK_Rachunki(idSesji, defAPI.RK_A_LISTA_NRB);
        System.out.println("PIK_Rachunki  : " + wynik); 
        
        String[] out = new String[5];
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("Liczba rekordów: " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                int i = 0;
                do{
                    System.out.println(++i + " : ");
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.Modulo", out);
                    System.out.println("Rk.Modulo : " + out[0]);
                    zapiszZmienna("iModZS", out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.Konto", out);
                    System.out.println("Rk.Konto : " + out[0]);
                    zapiszZmienna("iKonZS", out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.Uwaga", out);
                    System.out.println("Rk.Uwaga : " + out[0]);
                    zapiszZmienna("iUwaZS", out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.ID", out);
                    System.out.println("Rk.ID : " + out[0]);
                    return out[0] + "";
                    
                }while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }else{
            wyswietlBlad(0);
        }
        separator();
        return "";
    }
    
    void PIK_ZleceniaStale1MPPZew() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        
        String IDR = "", NRBo = "", BNo = "", kwota = "", kwotaVAT = "", currDate = "", nr = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        IDR = wczytajZmienna("iIDRB");
        
        currDate = dateFormat.format(date);
        nr = wczytajZmienna("iNrKolejny");
        
        NRBo = "30109010987651004978704473".replace(" ","");
        BNo = NRBo.substring(2,10);
        kwota = "1.11";
        kwotaVAT = "0.11";
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypZlecenia",defAPI.Z_BUFOR, "10");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Powtarzalność",defAPI.Z_BUFOR, "3");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDR-ekWłasny",defAPI.Z_BUFOR, IDR); 
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.ModuloKlienta",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Konto",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Uwaga",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.RodzajZlecenia",defAPI.Z_BUFOR, "Prąd MPP");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.KwotaVAT",defAPI.Z_BUFOR, kwotaVAT);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDC",defAPI.Z_BUFOR, "NIP Klienta");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.INV",defAPI.Z_BUFOR, "FV/Auto/APIMPP/" + currDate + "/" + nr);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TXT",defAPI.Z_BUFOR, "ZS MPP API");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Kwota",defAPI.Z_BUFOR, kwota);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Waluta",defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.BankAdresat",defAPI.Z_BUFOR, BNo);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.R-ekAdresata",defAPI.Z_BUFOR, NRBo);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDR-ekAdresata",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Adresat",defAPI.Z_BUFOR, "Adresat\nZlecenia\nStałego\nRegres");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Tytuł",defAPI.Z_BUFOR, "Tytuł\nZlecenia\nStałego\nRegres");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.NIP",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDP",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypSkładki",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.NazwaNadawcy",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypIdentyfik.",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Identyfikator",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypWpłaty",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Deklaracja",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.NrDeklaracji",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Priorytet",defAPI.Z_BUFOR, "300");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.DzieńRealizacji",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypDnia",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.CoIlePowtarzać",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.MiesiącStartu",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.MiesiącKońca",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.DataRealizacji",defAPI.Z_BUFOR, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.KanałDystrybucji",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypOpłaty",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Opłata",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.MusiZatwierdzić",defAPI.Z_BUFOR, "2");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.ObsługaZaległych",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypopłatyZaleg.",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.OpłataZaległości",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.BlokadaŚr",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.KanałWgPro",defAPI.Z_BUFOR, "WWW");
        
        wynik = api.PIK_ZleceniaStale(idSesji, api.ZS_A_DODANIE, "");
        System.out.println("PIK_ZleceniaStale : " + wynik);
        String[] wartosc = new String[1];
        
        if(wynik == WYNIK_OK){

        }else{
            wyswietlBlad(1);
        }
        
        wynik = Integer.parseInt(nr);
        wynik++;
        zapiszZmienna("iNrKolejny", "" + wynik);
        
        separator();
    }
    
    void PIK_ZleceniaStale1MPPWew() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        
        String IDR = "", NRBo = "", BNo = "", kwota = "", kwotaVAT = "", currDate = "", nr = "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        
        IDR = wczytajZmienna("iIDRB");
        
        currDate = dateFormat.format(date);
        nr = wczytajZmienna("iNrKolejny");
        
        NRBo = NRBSrodki.replace(" ","");
        BNo = NRBo.substring(2,10);
        kwota = "1.11";
        kwotaVAT = "0.11";
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypZlecenia",defAPI.Z_BUFOR, "10");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Powtarzalność",defAPI.Z_BUFOR, "3");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDR-ekWłasny",defAPI.Z_BUFOR, IDR); 
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.ModuloKlienta",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Konto",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Uwaga",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.RodzajZlecenia",defAPI.Z_BUFOR, "Prąd MPP");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.KwotaVAT",defAPI.Z_BUFOR, kwotaVAT);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDC",defAPI.Z_BUFOR, "NIP Klienta");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.INV",defAPI.Z_BUFOR, "FV/Auto/APIMPP/" + currDate + "/" + nr);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TXT",defAPI.Z_BUFOR, "ZS MPP API");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Kwota",defAPI.Z_BUFOR, kwota);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Waluta",defAPI.Z_BUFOR, "PLN");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.BankAdresat",defAPI.Z_BUFOR, BNo);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.R-ekAdresata",defAPI.Z_BUFOR, NRBo);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDR-ekAdresata",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Adresat",defAPI.Z_BUFOR, "Adresat\nZlecenia\nStałego\nRegres");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Tytuł",defAPI.Z_BUFOR, "Tytuł\nZlecenia\nStałego\nRegres");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.NIP",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDP",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypSkładki",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.NazwaNadawcy",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypIdentyfik.",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Identyfikator",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypWpłaty",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Deklaracja",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.NrDeklaracji",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Priorytet",defAPI.Z_BUFOR, "300");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.DzieńRealizacji",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypDnia",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.CoIlePowtarzać",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.MiesiącStartu",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.MiesiącKońca",defAPI.Z_BUFOR, "");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.DataRealizacji",defAPI.Z_BUFOR, DataKsiegowaDef);
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.KanałDystrybucji",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypOpłaty",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Opłata",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.MusiZatwierdzić",defAPI.Z_BUFOR, "2");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.ObsługaZaległych",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TypopłatyZaleg.",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.OpłataZaległości",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.BlokadaŚr",defAPI.Z_BUFOR, "0");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.KanałWgPro",defAPI.Z_BUFOR, "WWW");
        
        wynik = api.PIK_ZleceniaStale(idSesji, api.ZS_A_DODANIE, "");
        System.out.println("PIK_ZleceniaStale : " + wynik);
        String[] wartosc = new String[1];
        
        if(wynik == WYNIK_OK){

        }else{
            wyswietlBlad(1);
        }
        
        wynik = Integer.parseInt(nr);
        wynik++;
        zapiszZmienna("iNrKolejny", "" + wynik);
        
        separator();
    }
    
    void PIK_ZleceniaStale2MPP() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String idZlec = "", currDate = "", nr = "";
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        currDate = dateFormat.format(date);
        nr = wczytajZmienna("iNrKolejny");
        idZlec = wczytajZmienna("oIdZlecSta");
        
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.KanałDystrybucji",defAPI.Z_BUFOR, "WWW");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.RodzajZlecenia",defAPI.Z_BUFOR, "Cyklon-MPP");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.Kwota",defAPI.Z_BUFOR, "22.22");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.KwotaVAT",defAPI.Z_BUFOR, "2.22");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.IDC",defAPI.Z_BUFOR, "NIP Klienta ZM");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.INV",defAPI.Z_BUFOR, "FV/Auto/APIMPP/" + currDate + "/" + nr + "/KOR");
        wynik = api.PIK_DoZmiennej(idSesji, "ZlecSt.TXT",defAPI.Z_BUFOR, "ZS MPP API Zmiana");
        
        wynik = api.PIK_ZleceniaStale(idSesji, api.ZS_A_ZMIANA, idZlec);
        System.out.println("PIK_ZleceniaStale : " + wynik);
        
        if(wynik == WYNIK_OK){

        }else{
            wyswietlBlad(0);
        }
        
        wynik = Integer.parseInt(nr);
        wynik++;
        zapiszZmienna("iNrKolejny", "" + wynik);
        
        separator();
        
    }
    
    void PIK_UmowaKR2() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String IDU = "";
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        IDU = wczytajZmienna("IDKred");

        //Wejściowe
        api.PIK_DoZmiennej (idSesji, "KR_Operator", defAPI.Z_BUFOR, "INTERCOMP");
        api.PIK_DoZmiennej (idSesji, "KR_IdUmowy", defAPI.Z_RACH, IDU);
        api.PIK_DoZmiennej (idSesji, "KR_Dyspozycja", defAPI.Z_RACH, "2");
        //Wyjściowe
        api.PIK_DoZmiennej (idSesji, "KR_Status", defAPI.Z_RACH, "");
        api.PIK_DoZmiennej (idSesji, "KR_BladNr", defAPI.Z_RACH, "");
        api.PIK_DoZmiennej (idSesji, "KR_BladOpis", defAPI.Z_MEMO, "");
        
        
        wynik = api.PIK_UmowaKR(idSesji, api.KR_A_ZATWIERDZENIE);
        System.out.println("PIK_UmowaKR(2) : " + wynik);
        String out [] = new String[10];
        
        if (wynik == 0){
            wyswietlBlad (1);

            api.PIK_ZeZmiennej(idSesji, "KR_Status", out);
            System.out.println("KR_Status: " + out[0]);
        }
        else{
            api.PIK_ZeZmiennej(idSesji, "KR_Status", out);
            System.out.println("KR_Status: " + out[0]);
        }
        separator();
    }
    
    void PIK_UmowaKR4() throws IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik;
        String IDU = "", NRBP = "", kwota = "";
        
        wynik = api.PIK_UsunWszystkieZmienne (idSesji);
        System.out.println("PIK_UsunWszystkieZmienne  : " + wynik);
        
        IDU = wczytajZmienna("IDKred");
        NRBP = wczytajZmienna("oNRB");
        
        kwota = "20000.00";
        
        
        //Wejściowe
        api.PIK_DoZmiennej (idSesji, "KR_NumerRef", defAPI.Z_BUFOR, "");
        api.PIK_DoZmiennej (idSesji, "KR_IdUmowy", defAPI.Z_RACH, IDU); // Tran1000
        api.PIK_DoZmiennej (idSesji, "KR_KwotaTranszy", defAPI.Z_KWOTA, kwota);
        api.PIK_DoZmiennej (idSesji, "KR_RodzajOperacji", defAPI.Z_KWOTA, "4");
        api.PIK_DoZmiennej (idSesji, "KR_Beneficjent", defAPI.Z_MEMO, "Os");
        api.PIK_DoZmiennej (idSesji, "KR_KodProwizji", defAPI.Z_RACH, "2008"); //Za udzielenie kredytu
        api.PIK_DoZmiennej (idSesji, "KR_KwotaProwizji", defAPI.Z_KWOTA, "1.00");
        api.PIK_DoZmiennej (idSesji, "KR_KodProwizji2", defAPI.Z_RACH, ""); //Za udzielenie kredytu
        api.PIK_DoZmiennej (idSesji, "KR_KwotaProwizji2", defAPI.Z_KWOTA, "0");
        api.PIK_DoZmiennej (idSesji, "KR_KwotaWyplaty", defAPI.Z_KWOTA, kwota);
        
        //Wyjściowe
        api.PIK_DoZmiennej (idSesji, "KR_NRB", defAPI.Z_BUFOR, "");
        api.PIK_DoZmiennej (idSesji, "KR_Status", defAPI.Z_RACH, "");
        api.PIK_DoZmiennej (idSesji, "KR_BladNr", defAPI.Z_RACH, "");
        api.PIK_DoZmiennej (idSesji, "KR_BladOpis", defAPI.Z_MEMO, "");
        
        
        wynik = api.PIK_UmowaKR(idSesji, api.KR_A_KSIEGOWANIE);
        System.out.println("PIK_UmowaKR(4) : " + wynik);
        String out [] = new String[10];
        
        if (wynik == 0){
            System.out.println("PIK_UmowaKR(4)  : " + wynik);
            wyswietlBlad (1);
        }
        else{
            api.PIK_ZeZmiennej(idSesji, "KR_NRB", out);
            System.out.println("KR_NRB: " + out[0]);
            api.PIK_ZeZmiennej(idSesji, "KR_Status", out);
            System.out.println("KR_Status: " + out[0]);
        }
        separator();
    }
    
    String Pesel(){
        String pesel = "";
        String plec = "";
        String data = "";
        int suma = 0;
        
        Random rand = new Random();
        
        int Y = rand.nextInt(100)+1900;
        int M = rand.nextInt(12)+1;
        int D = 0;
        
        if(M == 1 || M == 3 || M == 5 || M == 7 || M == 8 || M == 10 || M == 12){
            D = rand.nextInt(31)+1;
        }
        else if(M == 4 || M == 6 || M == 9 || M == 11){
            D = rand.nextInt(30)+1;
        }
        else if(M == 2){
            D = rand.nextInt(28) + 1;
        }
        
        int Yt = 0;
        int Mt = 0;
        int Dt = 0;
        
        Yt = (Y % 100);
        Mt = M;
        Dt = D;
        
        if(Y >= 1800 && Y <= 1899){
                Mt = (M + 80);
        } 
        else if(Y >= 2000 && Y <= 2099){
            Mt = (M + 20);
        }
        else if(Y >= 2100 && Y <= 2199){
            Mt = (M + 40);
        }
        else if(Y >= 2200 && Y <= 2299){
            Mt = (Mt + 60);
        }
        
        if(Yt % 100 < 10){
            pesel = pesel + "0";
        }
        
        pesel = pesel + "" + Yt + "";
        if(Mt < 10){
            pesel = pesel + "0";
        }
        pesel = pesel + "" + Mt + "";
        if(Dt < 10){
            pesel = pesel + "0";
        }
        pesel = pesel + "" + Dt + "";
        
        for (int i = 0; i < 4; i++){
            pesel = pesel + "" + rand.nextInt(10) + "";
        }
        
        suma = 1 * Integer.parseInt(pesel.charAt(0) + "") +
                3 * Integer.parseInt(pesel.charAt(1) + "") +
                7 * Integer.parseInt(pesel.charAt(2) + "") +
                9 * Integer.parseInt(pesel.charAt(3) + "") +
                1 * Integer.parseInt(pesel.charAt(4) + "") +
                3 * Integer.parseInt(pesel.charAt(5) + "") +
                7 * Integer.parseInt(pesel.charAt(6) + "") + 
                9 * Integer.parseInt(pesel.charAt(7) + "") +
                1 * Integer.parseInt(pesel.charAt(8) + "") +
                3 * Integer.parseInt(pesel.charAt(9) + "") ;
        
        int k = 10 - suma % 10;
        
        if (k == 10){
            k = 0;
        }
        
        pesel = pesel + "" + k + "";
        
        if(Integer.parseInt(pesel.charAt(9) + "") % 2 == 0){
            plec = "Kobieta";
        }
        else{
            plec = "Mężczyzna";
        }
        
        data = Y + "." + pesel.substring(2,4) + "." + pesel.substring(4,6);
        System.out.println("Płeć: " + plec);
        System.out.println("Rok urodzenia: " + data);
        System.out.println("Pesel: " + pesel);
	return pesel;

    }
    
    String NIP()
    {
        String nip = "";
        int tmp = 0;
        int suma = 0;
        int w[] = {6, 5, 7, 2, 3, 4, 5, 6, 7};
        Random rand = new Random();

        for(int i=0; i<9; i++){
            if(i==0 || i==2){
                tmp = rand.nextInt(9) + 1;
            }
            else{
                tmp = rand.nextInt(10);
            }
            nip = nip + "" + tmp;
            suma = suma + w[i] * tmp;
        }
        nip = nip + "" + suma%11 + "";
        if(nip.length() == 10){
            System.out.println("NIP: " + nip);
            return nip;
        }
        else{
            System.out.println("Wygenerowałem dziwny NIP o długości różnej od 10: " + nip + "\nGeneruję jeszcze raz.");
            nip = NIP();
        }
        return nip;
    }
    
    String NRB_ZUS_NIP()
    {	
        String nip = NIP();
        String NRB = "";
        String tmp_s = "";
        int k = 0;
        int suma = 0;
        int nrWiesza = 0;
        int tmp = 0;
        int w [] = {57, 93, 19, 31, 71, 75, 56, 25, 51, 73, 17, 89, 38, 62, 45, 53, 15, 50, 5, 49, 34, 81, 76, 27, 90, 9, 30, 3, 10, 1};
        String s = "";
        Random rand = new Random();
        
        NRB = NRB + "60000002" + "026" + rand.nextInt(10) + "" + rand.nextInt(10) + "" + rand.nextInt(10) + nip; 

        tmp_s = NRB + "252100";

        for(int i=0; i<30; i++){
            suma = suma + Integer.parseInt(tmp_s.charAt(i) + "") * w[i];
        }

        k = 98 - (suma % 97);

        if(k < 10){
            NRB = "0" + k + "" + NRB;
        }
        else{
            NRB = "" + k + "" + NRB;
        }
        
        System.out.println("Wygenerowałem NRB ZUS: " + NRB);
        return NRB;
    }
    
    String NRB_US_NIP()
    {
        String nip = NIP();

        String NRB = "";
        String tmp_s = "";
        int k = 0;
        int suma = 0;
        int nrWiesza = 0;
        int tmp = 0;
        int w [] = {57, 93, 19, 31, 71, 75, 56, 25, 51, 73, 17, 89, 38, 62, 45, 53, 15, 50, 5, 49, 34, 81, 76, 27, 90, 9, 30, 3, 10, 1};
        String s = "";

        NRB = NRB + "10100071" + "2222" + nip + "00";

        tmp_s = NRB + "252100";

        for(int i=0; i<30; i++){
            suma = suma + Integer.parseInt(tmp_s.charAt(i) + "") * w[i];
        }

        k = 98 - (suma % 97);

        if(k < 10){
            NRB = "0" + k + "" + NRB;
        }
        else{
            NRB = "" + k + "" + NRB;
        }
        
        System.out.println("Wygenerowałem NRB US na podstawie NIP: " + NRB);
        return NRB;
    }
    
    String NRB_US_PESEL()
    {
        String pesel = Pesel();
        
        String NRB = "";
        String tmp_s = "";
        int k = 0;
        int suma = 0;
        int nrWiesza = 0;
        int tmp = 0;
        int w[] = {57, 93, 19, 31, 71, 75, 56, 25, 51, 73, 17, 89, 38, 62, 45, 53, 15, 50, 5, 49, 34, 81, 76, 27, 90, 9, 30, 3, 10, 1};
        String s = "";

        NRB = NRB + "10100071" + "2221" + pesel + "0";

        tmp_s = NRB + "252100";

        for(int i=0; i<30; i++){
            suma = suma + Integer.parseInt(tmp_s.charAt(i) + "") * w[i];
        }

        k = 98 - (suma % 97);

        if(k < 10){
            NRB = "0" + k + "" + NRB;
        }
        else{
            NRB = "" + k + "" + NRB;
        }

        System.out.println("Wygenerowałem NRB US na podstawie PESEL: " + NRB);
        return NRB;
    }
    
    void PIK_Rachunki5_ID_AN_DEF_KR() throws FileNotFoundException, IOException{
        System.out.println("Funkcja nr: " + fNr + " / " + iloscF);
        fNr++;
        int wynik = 0;
        String NRB = wczytajZmienna("oNRBKr");
        
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.NRB", defAPI.Z_BUFOR , NRB);
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.Modulo", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.Konto", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.Uwaga", defAPI.Z_BUFOR , "");
        wynik = api.PIK_DoZmiennej(idSesji, "Rk.ID", defAPI.Z_BUFOR , "");
        
        wynik = api.PIK_Rachunki(idSesji, defAPI.RK_A_LISTA_NRB);
        System.out.println("PIK_Rachunki  : " + wynik); 
        
        String[] out = new String[5];
        if(wynik == WYNIK_OK){
            wynik = api.PIK_LiczbaRekordow(idSesji);
            System.out.println("Liczba rekordów: " + wynik);
            if(api.PIK_PierwszyRekord(idSesji) == WYNIK_OK){
                int i = 0;
                do{
                    System.out.println(++i + " : ");
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.Modulo", out);
                    System.out.println("Rk.Modulo : " + out[0]);
//                    zapiszZmienna("iModZS", out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.Konto", out);
                    System.out.println("Rk.Konto : " + out[0]);
//                    zapiszZmienna("iKonZS", out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.Uwaga", out);
                    System.out.println("Rk.Uwaga : " + out[0]);
//                    zapiszZmienna("iUwaZS", out[0]);
                    wynik = api.PIK_ZeZmiennej(idSesji, "Rk.ID", out);
                    System.out.println("Rk.ID : " + out[0]);
                    zapiszZmienna("IDAnDefKred", out[0]);
                }while(api.PIK_NastepnyRekord(idSesji) == WYNIK_OK);
            }
        }else{
            wyswietlBlad(1);
        }
        separator();
    }
    
    public static void main(String[] args) throws IOException {
        DefApi_R_BS_EXE_5x test = new DefApi_R_BS_EXE_5x();
        test.ustawParametry(args);
        test.akcja();
    }
    
}
