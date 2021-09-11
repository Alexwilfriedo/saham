package com.digital.util;

/**
 * @author babacoul
 */
public class Constant {

  public static final int JPA_CONSTRAINTS_PASSWORD_MIN = 6;

  public static final int JPA_CONSTRAINTS_PASSWORD_MAX = 12;

  public static final int JPA_CONSTRAINTS_SHORTEST = 2;

  public static final int JPA_CONSTRAINTS_SHORT = 60;

  public static final int JPA_CONSTRAINTS_MEDIUM = 100;

  public static final int JPA_CONSTRAINTS_THUMBNAIL = 10_000;


  //paramettres Aroli group
  public final static String userAroli ="digital";
  public final static String passwordAroli = "dgt@2017";
  public final static String urlAroli ="http://gateway2.arolitec.com/interface/senderv2.php";

  //paramettre InfoBip
  public final static String userInfoBip ="DIGITALIT";
  public final static String passwordInfoBip ="Steevebiko@27";
    public static String contrat_desherence_message ="Pour les informations complémentaires sur ce contrat, prière nous contacter directement en agence ou au 20-25-97-10 / 20-25-97-09.";

    //Mail
  public boolean mode_prod = true;
  public final static String mail_prod = "info.vie.ci@sahamassurance.com";
  //public final static String mail_prod = "b.coulibaly@digit-al.ci";
  public final static String mail_test = "s.gaye@digit-al.ci";
  public final static String mail_dev = "lathe.marthe@sahamassurance.com";



  //liste des indentifiant des corporate
  public final static  long[] refCorporate = {
          3000001,3000002,3000003,3000004,3000005,
          3000006,3000007,3000008,3000009,3000010,
          3000011,3000012,3000013,3000014,3000015,
          3000016,3000017,3000018,3000019,3000020,
          3000021,3000022,3000023,3000024,3000025,
          3000026,3000027,3000028,3000029,3000030,
          3000031,3000032,3000033,3000034,3000035,
          3000036,3000037,3000050
  };

  public final static  long[] refCorporateOfAsk = {
          3000003,3000005,3000006,3000008,3000010,3000021
  };

  public final static long idOrange = 3000027;

  public final static  Long[] refCorporates = {
          3000001L,3000002L,3000003L,3000004L,3000005L,
          3000006L,3000007L,3000008L,3000009L,3000010L,
          3000011L,3000012L,3000013L,3000014L,3000015L,
          3000016L,3000017L,3000018L,3000019L,3000020L,
          3000021L,3000022L,3000023L,3000024L,3000025L,
          3000026L,3000027L,3000028L,3000029L,3000030L,
          3000031L,3000032L,3000033L,3000034L,3000035L,
          3000036L,3000037L,3000050L
  };
  public final static String [] saham_togo_test_contacts ={"22509206200","22899750295","22899208333","22896804616","22896801762","22898902657","22898902866","22890237300","22578348467"};

  public final static String [] coqivoire_contactsold ={"07600131","09602403","48622243","06328153","01391475","49505047","05741514","57595941","09145611","04481915","05972961",
                                                      "05312147","05312147","05457144","09557347","05492496","78084423","08723143",
                                                     "56645674","71429530","49347749","05917895","06246050","07946904","05366581","02521363","45245751",
                                                      "07062084","07062084","02521363","07824950","05587591","05121573","40433111","48745374",
                                                     "77095414","08450164","05027738","05199653","05222381","07534738","09264276","04768853",
                                                      "06663955","05061731","59890389","07948384","55953930","08575480","07077923","77692488","78075885",
                                                     "47734527","08785977","07754060","47541136","78348467","09206200"};

  public final static String [] coqivoire_contacts ={ "09602403","48622243","06328153","49347749","08723143","49505047","05741514","07946904","57595941",
"05366581","05917895","05972961","05312147","09557347","51663246","05492496","01391475","05457144",
"71429530","43049776","04481915","09145611","06246050","07062084","02521363","07824950","58891808",
"07988289","09264276","04768853","07065975","48745374","08450164","07534738","05027738","40433111",
"45245751","87776918","06663955","77095414","05121573","59890389","55953930","09685001","07948384",
"07077923","08575480","77692488","48038028","78075885","77249095","07754060","08785977","49230831","87734737","09206200"};
  public final static String coqivoire_message = "   Ce n'est pas fini, vous pouvez encore gagner les 2 glacières Coqivoire et la somme de 200 000FCFA. Vendez plus et gagnez gros!";
  public final static String saham_togo_message_test = "Ceci est un test sur les 6 numeros de la liste des recepteurs des accuses de reception";


  public final static String CONNECTED = "connected";
  public final static String DISCONNECTED = "disconnected";
  public final static String USERNAME_MANAGER_SAHAM ="19000000";
  public final static String PRIME ="Prime";
  public final static String IMPAYE ="impayé";
  public final static String PAYE ="payé";


}
