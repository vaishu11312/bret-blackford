package bret.blackford.harvard.cscie160.hw3;

import java.util.Random;

/** {@code NamesEnum} this is an Enumeration type of proper names. List obtained from http://www.lost-colony.com/namelist.html (used web-site to obatin a random listing of names)
 *
 * @author M. Bret Blackford (20849347)
 * @version 1.0
 * @since October 1, 2011
 */
public enum NamesEnum {
    MasterRalfeLane,
    MasterPhilipAmades,
    MasterThomasHariot,
    MasteeActon,
    MasterEdwardStafford,
    ThomasLuddington,
    MasterMaruyn,
    MasterGardyner,
    CaptainVaughan,
    MasterKendall,
    MasterPrideox,
    RobertHolecroft,
    RiseCourtenay,
    MasterHughRogers,
    ThomasFoxe,
    EdwardNugen,
    DarbyGlande,
    EdwardKelle,
    IohnGostigo,
    ErasmusClefs,
    EdwardKetcheman,
    IohnLinsey,
    ThomasRottenbury,
    RogerDeane,
    IohnHarris,
    FrauncisNorris,
    MatheweLyne,
    EdwardKettell,
    ThomasWisse,
    RobertBiscombe,
    WilliamBackhouse,
    WilliamWhite,
    HenryPotkin,
    DennisBarnes,
    IosephBorges,
    DoughanGannes,
    WilliamTenche,
    RandallLatham,
    ThomasHulme,
    WalterMyll,
    RichardGilbert,
    SteuenPomarie,
    IohnBrocke,
    BennetHarrye,
    IamesStevenson,
    CharlesStevenson,
    ChristopherLowde,
    IeremieMan,
    IamesMason,
    DauvidSalter,
    RichardIreland,
    ThomasBookener,
    WilliamPhilippes,
    RandallMayne,
    MasterThomasHarvye,
    MasterSnelling,
    MasterAnthonyRusse,
    MasterAllyne,
    MasterMichelPolyson,
    IohnCage,
    ThomasParre,
    WilliamRandes,
    GefferyChurchman,
    WilliamFarthowe,
    IohnTaylor,
    PhilppeRobyns,
    ThomasPhillippes,
    ValentineBeale,
    IamesSkinner,
    GeorgeEseuen,
    JohnChaundeler,
    PhilipBlunt,
    RichardPoore,
    RobertYong,
    MarmadukeConstable,
    ThomasHesket,
    WilliamWasse,
    IohnFeuer,
    Daniel,
    ThomasTaylor,
    RichardHumfrey,
    IohnWright,
    GabriellNorth,
    BennetChappell,
    RichardSare,
    IamesLasie,
    Smolkin,
    ThomasSmart,
    Robert,
    IohnEvans,
    RogerLarge,
    HumfreyGarden,
    FrauncisWhitton,
    RowlandGriffyn,
    WilliamMillard,
    IohnTwyt,
    EdwardeSeklemore,
    IohnAnwike,
    ChristopherMarshall,
    DauidWilliams,
    NicholasSwabber,
    EdwardChipping,
    SyluesterBeching,
    VincentCheyne,
    HaunceWalters,
    EdwardBarecombe,
    ThomasSkeuelabs,
    WilliamWalters,
    Cora,
    Neva,
    David,
    Michelle,
    EndOfFile;

    /** Will provide a random name from among the list of names.
     * @return
     */
    public static String getRandomName() {

       int totalNames = NamesEnum.values().length;
       int nameInt = getRandom0toX(totalNames);
       
       String newName = "" + NamesEnum.values()[nameInt];
       
       return newName;
    }
    
    private static int getRandom0toX(int end) {
       Random randomNumberGenerator = new Random();
       int randomInt = 0;
       randomInt = randomNumberGenerator.nextInt(end);
       return randomInt;
    }
}
