package sortiedechien.fr.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import sortiedechien.fr.dao.ArbreDao;
import sortiedechien.fr.dao.BaseDao;
import sortiedechien.fr.data.Arbre;
import sortiedechien.fr.data.Parc;
import sortiedechien.fr.retrofit.INetworkNotifier;
import sortiedechien.fr.retrofit.NetworkParkList;
import sortiedechien.fr.retrofit_arbres.NetworkTreeList;

/**
 * Created by guillaume on 08/12/16.
 */

public class DbHandler extends SQLiteOpenHelper {

    //tables
    public static final String TABLE_PARCS = "PARC";
    public static final String TABLE_ARBRE = "ARBRE";
    //colonnes parc
    public static final String COLUMN_LIBELLE = "LIBELLE";
    public static final String COLUMN_POSITION_X = "POSITION_X";
    public static final String COLUMN_POSITION_Y = "POSITION_Y";
    public static final String COLUMN_point_d_eau = "POINT_D_EAU";
    public static final String COLUMN_ACCES_HANDICAPE = "ACCES_HANDICAPE";
    public static final String COLUMN_CHIEN_INTERDIT = "CHIEN_INTERDIT";
    public static final String COLUMN_SURFACE = "SURFACE";
    public static final String COLUMN_SANITAIRE = "SANITAIRE";
    public static final String COLUMN_JEUX = "JEUX";
    public static final String COLUMN_PARC_CLOS = "PARC_CLOS";
    //colones arbres
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ADRESSE = "ADRESSE";
    public static final String COLUMN_LONGITUDE = "LOGITUDE";
    public static final String COLUMN_LATTITUDE = "LATTITUDE";
    //REQUETES
    private static String DROP_PARC = "DROP TABLE IF EXISTS PARC";
    private static String DROP_ARBRE = "DROP TABLE IF EXISTS ARBRE";
    private static String CREATE_PARC = "CREATE TABLE PARC ( LIBELLE VARCHAR PRIMARY KEY, POSITION_X VARCHAR, POSITION_Y VARCHAR, point_eau BOOLEAN, acces_handicape BOOLEAN, chien_interdit BOOLEAN, surface NUMBER, sanitaire BOOLEAN, jeux BOOLEAN, parc_clos BOOLEAN);";
    private static String CREATE_TREE = "CREATE TABLE ARBRE(ID TEXT,ADRESSE TEXT, LATITUDE TEXT,LONGITUDE TEXT);";
    private Context context;
    public static final String CREATE_DB =
            "CREATE TABLE PARC ( LIBELLE VARCHAR PRIMARY KEY, POSITION_X VARCHAR, POSITION_Y VARCHAR, point_eau BOOLEAN, acces_handicape BOOLEAN, chien_interdit BOOLEAN, surface NUMBER, sanitaire BOOLEAN, jeux BOOLEAN, parc_clos BOOLEAN);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc du grand-blottereau','47.2276','-1.50934',1,1,0,192660,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square benoni goulin','47.2023','-1.55138',0,0,0,1979,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square rue jules sebilleau','47.2053','-1.53153',0,0,0,1820,0,0,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc say','47.2123','-1.5685',0,0,0,8010,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square de la marseillaise','47.2078','-1.59202',0,0,0,1896,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square des marthyrs irlandais','47.2081','-1.60983',1,1,0,10710,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square du bois de la musse','47.2049','-1.61028',0,0,0,7902,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc de la boucardiere','47.2015','-1.59801',1,0,0,26010,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square gaston michel','47.2351','-1.57141',0,0,1,1788,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Jeux de boules proust bergson','47.2545','-1.57074',0,0,0,1344,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc du petit port','47.2476','-1.56688',0,0,0,493800,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc de beaulieu','47.2114','-1.52053',1,1,0,12970,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square gabriel chÃ©reau','47.2111','-1.56282',1,0,0,938,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square des courtils','47.2008','-1.59307',0,1,0,4198,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Cimetiere paysager','47.2709','-1.58538',0,1,1,71937,1,0,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square saint franÃ§ois','47.2183','-1.54513',0,1,0,1747,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Jardin des quatre jeudis','47.2659','-1.51974',0,1,0,13010,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Jardin du nadir','47.267','-1.519',0,1,0,2897,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Jardin du zenith','47.2689','-1.51677',0,1,0,1595,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Jardin des farfadets','47.2669','-1.52127',0,0,0,3747,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc de la chantrerie','47.2875','-1.52446',0,1,0,176600,1,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc de la moutonnerie','47.221554','-1.533176',0,1,0,15670,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Abords du stade de la beaujoire','47.2569','-1.52301',0,0,0,105400,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square du vieux parc de sÃ¨vre','47.1829','-1.51965',0,0,0,331,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Le parc des chantiers','47.2059','-1.56715',0,1,0,13000,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc de la gaudiniere','47.2452','-1.57952',1,1,0,125000,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square de la halvÃ¨que','47.2544','-1.52001',0,1,0,28170,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square jean heurtin','47.215','-1.54468',0,0,0,813,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc potager de la crapaudine','47.1909','-1.53078',1,1,0,16580,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Place de la guirouÃ©e','47.2086','-1.5274',0,0,0,1345,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square barbara','47.2662','-1.52304',0,0,0,396,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square du lait de mai','47.2121','-1.54823',1,1,1,1747,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square des maraiches','47.2463','-1.51608',1,1,1,5672,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square louis feuillade','47.2306','-1.58528',0,0,0,3928,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Jardin mabon','47.2077','-1.55964',0,0,0,2686,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Jardin des nectars','47.2381','-1.58255',1,1,1,2027,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Le jardin des fonderies','47.2057','-1.54489',0,0,0,3600,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Aire de jeux des renards','47.2578','-1.55923',0,0,0,28030,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Espace de jeux de belle ile','47.2453','-1.54166',0,1,0,2791,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc pablo picasso','47.2149','-1.53688',0,1,0,1605,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square psalette','47.218','-1.55036',0,0,0,2387,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Douves du chÃ¢teau des ducs','47.2163','-1.54916',0,0,0,16410,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square saint pasquier','47.2288','-1.56601',0,1,0,395,1,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Jardin confluent','47.1969','-1.5448',0,0,0,28040,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square fÃ©lix thomas','47.2311','-1.55332',0,1,1,1796,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc potager croissant','47.2355','-1.52541',1,1,0,22320,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Place wattignies','47.2038','-1.54795',0,0,0,2753,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square pilotiÃ¨re','47.245','-1.52471',0,0,0,3792,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc potager amande','47.2631','-1.57095',0,0,0,84670,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square hongrie','47.2164','-1.52407',0,1,1,2976,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc potager fournillÃ¨re','47.2125','-1.59051',1,1,0,31570,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square zac pilleux nord','47.2078','-1.58038',0,1,1,3803,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc des oblates','47.2003965','-1.5850374',0,0,0,30000,0,0,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc des dervalliÃ¨res','47.2248','-1.59412',0,1,0,82110,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Jardin d''enfants de procÃ©','47.2222','-1.58153',1,0,1,5800,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc de proce','47.2243','-1.58156',1,1,0,113000,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square amiral halgand','47.2175','-1.55443',0,0,0,1298,1,0,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Jardin des plantes','47.2198','-1.54292',1,1,0,73280,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Cours cambrone','47.212','-1.56308',0,0,0,8762,1,0,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square louis bureau','47.2129','-1.56489',1,1,0,1947,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square ile de versailles','47.226','-1.55438',1,1,0,15000,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square maquis de saffrÃ©','47.2221','-1.55188',0,1,0,3000,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square jean-batiste daviais','47.212','-1.55775',0,0,0,2965,0,0,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Jardin des cinq sens','47.2075','-1.53367',1,1,0,5677,1,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square charles housset','47.2027','-1.60834',0,0,0,1100,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square faustin hÃ©lie','47.2179','-1.56347',0,0,0,9441,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc de la beaujoire','47.2629','-1.53242',1,1,0,133000,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc du plessis tison','47.235','-1.53608',0,1,1,13940,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square maurice schwob','47.2011','-1.58072',1,0,1,7203,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square elisa mercoeur','47.2142','-1.55037',0,0,0,27120,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square marcel launay','47.2039','-1.53609',0,0,0,10300,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square jean le gigant','47.2042','-1.59294',1,0,0,6430,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square toussaint louverture','47.195','-1.61559',0,1,0,4737,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc des capucins','47.2253','-1.56276',1,1,0,8550,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc de la noe mitrie','47.2277','-1.52675',1,0,1,17200,1,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square jean-baptiste barre','47.2226','-1.52753',0,0,1,1724,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square jules brÃ©choir','47.2254','-1.52259',0,1,1,2720,0,0,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc de malakoff','47.2182','-1.51796',1,1,0,42260,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square gustave roch','47.2052','-1.54996',0,0,0,3279,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Place basse mar','47.2081','-1.52521',0,1,0,1124,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square vertais','47.2024','-1.54659',1,1,0,12320,1,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square du prinquiau','47.2122','-1.58708',0,1,1,2650,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square canclaux','47.2144','-1.57444',0,1,0,1634,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square des combattants d''afrique du nord','47.2093','-1.57075',0,1,1,1811,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square de l''edit de nantes','47.2144','-1.56766',1,0,0,737,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square marcel moisan','47.2022','-1.57732',0,0,1,691,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square pascal lebee','47.2162','-1.56342',0,0,0,539,1,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Square etienne destranges','47.2192','-1.56382',0,0,0,701,0,0,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Centre social de la pilotiÃ¨re','47.2452','-1.52404',0,0,0,10830,0,1,1);\n" +
                    "INSERT INTO \"parc\" VALUES('Square augustin fresnel','47.2431','-1.52293',0,1,0,5600,0,1,0);\n" +
                    "INSERT INTO \"parc\" VALUES('Parc de broussais','47.2268','-1.52255',0,1,0,12130,0,1,0);\n" +
                    "CREATE TABLE arbre('ID' TEXT, 'ADRESSE' TEXT, 'LATITUDE' TEXT, 'LONGITUDE' TEXT);\n" +
                    "INSERT INTO 'arbre' VALUES('1','Avenue de Chanzy','47.22401','-1.545548');\n" +
                    "INSERT INTO 'arbre' VALUES('2','Quai de Versailles','47.224561','-1.553899');\n" +
                    "INSERT INTO 'arbre' VALUES('3','Rue de Miséricorde','47.22148','-1.56579');\n" +
                    "INSERT INTO 'arbre' VALUES('4','Rue Chanoine Durville','47.230984','-1.556271');\n" +
                    "INSERT INTO 'arbre' VALUES('5','Avenue du parc du procé','47.218143','-1.582285');\n" +
                    "INSERT INTO 'arbre' VALUES('6','Boulevard Lelasseur','47.231136','-1.569624');\n" +
                    "INSERT INTO 'arbre' VALUES('7','Boulevard de Doulon','47.226243','-1.523164');\n" +
                    "INSERT INTO 'arbre' VALUES('8','Boulevard Auguste Peneau','47.227506','-1.516715');\n" +
                    "INSERT INTO 'arbre' VALUES('9','Boulevard Robert Schuman','47.236148','-1.568332');\n" +
                    "INSERT INTO 'arbre' VALUES('10','Allée Duquesne','47.218637','-1.556829');\n" +
                    "INSERT INTO 'arbre' VALUES('11','Boulevard de la Prairie au Duc','47.205093','-1.556954');\n" +
                    "INSERT INTO 'arbre' VALUES('12','Boulevard Georges Mandel','47.201500','-1.539705');\n" +
                    "INSERT INTO 'arbre' VALUES('13','Rue du Docteur Paul Michaux','47.207572','-1.511358');\n" +
                    "INSERT INTO 'arbre' VALUES('14','Rue du Général Buat','47.231291','-1.538527');\n" +
                    "INSERT INTO 'arbre' VALUES('15','Boulevard de Launay','47.211121','-1.575404');\n" +
                    "INSERT INTO 'arbre' VALUES('16','Rue du Général Buat','47.229271','-1.540381');\n" +
                    "INSERT INTO 'arbre' VALUES('17','131 Boulevard Saint Aignan ','47.203430','-1.581249');\n" +
                    "INSERT INTO 'arbre' VALUES('18','Boulvard Jules Verne','47.237728','-1.532544');\n" +
                    "INSERT INTO 'arbre' VALUES('19','Boulevard des Poilus','47.229476','-1.527657');\n" +
                    "INSERT INTO 'arbre' VALUES('20','Route de Sainte-Luce','47.228818','-1.525905');\n" +
                    "INSERT INTO 'arbre' VALUES('21','Boulevard de la Liberté','47.205887','-1.587841');\n" +
                    "INSERT INTO 'arbre' VALUES('22','Boulevard de l''égalité','47.207875','-1.587348');\n" +
                    "INSERT INTO 'arbre' VALUES('23','Rue du Pré Hervé','47.237728','-1.532544');\n" +
                    "INSERT INTO 'arbre' VALUES('24','Avenue de la Valtaiserie','47.228471','-1.522537');\n" +
                    "INSERT INTO 'arbre' VALUES('25','Ile de Nantes','47.211288','-1.522450');\n" +
                    "INSERT INTO 'arbre' VALUES('26','Nantes Erdre','47.239630','-1.542977');\n" +
                    "INSERT INTO 'arbre' VALUES('27','Route de Saint Joseph','47.242990','-1.534264');\n" +
                    "INSERT INTO 'arbre' VALUES('28','Boulevard Jules Vernes','47.242715','-1.527795');\n" +
                    "INSERT INTO 'arbre' VALUES('29','Antarctique','-73.203046',' -14.360584');\n" +
                    "INSERT INTO 'arbre' VALUES('30','Boulevard Jules Vernes','47.237728','-1.532544');\n" +
                    "INSERT INTO 'arbre' VALUES('31','Boulevard Lelasseur','47.230834','-1.569979');\n" +
                    "INSERT INTO 'arbre' VALUES('32','Square Saint Pasquier','47.228724',' -1.565964');\n" +
                    "INSERT INTO 'arbre' VALUES('33','Rue Paul Bellamy','47.226819',' -1.560581');\n" +
                    "INSERT INTO 'arbre' VALUES('34','Rue Léopold Cassegrain','47.218566,','-1.558437');\n" +
                    "INSERT INTO 'arbre' VALUES('35','Allée Duquesne','47.217952','-1.557017');\n" +
                    "INSERT INTO 'arbre' VALUES('36','Boulevard du Lapacca','43.098137','-0.042247');\n" +
                    "INSERT INTO 'arbre' VALUES('37','Chemin du Petit Bois','47.188103','-1.539783');\n" +
                    "INSERT INTO 'arbre' VALUES('38','Place Pierre Semard','47.190139','-1.547412');\n" +
                    "INSERT INTO 'arbre' VALUES('39','Boulvard de l''Europe','47.198140','-1.507279');\n" +
                    "INSERT INTO 'arbre' VALUES('40','Avenue Louise Michelle','47.188566','-1.550447');\n" +
                    "INSERT INTO 'arbre' VALUES('41','Avenue Alexandre Plancher','47.186739','-1.559660');\n" +
                    "INSERT INTO 'arbre' VALUES('42','Avenue D''Anjou','47.183483','-1.567606');\n" +
                    "INSERT INTO 'arbre' VALUES('43','Rue de la Trocardière','47.181157','-1.571702');\n" +
                    "INSERT INTO 'arbre' VALUES('44','Rue du Chêne Creux','47.172461','-1.546289');\n" +
                    "INSERT INTO 'arbre' VALUES('45','Rue de la Quératière','47.163769','-1.538523');\n" +
                    "INSERT INTO 'arbre' VALUES('46','Allée de la Fragonette','47.171455','-1.557332');\n" +
                    "INSERT INTO 'arbre' VALUES('47','Rue des Palombes','47.194648','-1.502664');\n" +
                    "INSERT INTO 'arbre' VALUES('48','Rue de la Mutualité','47.190813','-1.504229');\n" +
                    "INSERT INTO 'arbre' VALUES('49','Rue de la Baugerie','47.204381','-1.519200');\n" +
                    "INSERT INTO 'arbre' VALUES('50','Boulevard Francois Blancho','47.205301','-1.530149');\n" +
                    "INSERT INTO 'arbre' VALUES('51','Boulvard du Val de Chézine','47.236815','-1.610248');\n" +
                    "INSERT INTO 'arbre' VALUES('52','Rue de la Commune de Paris 1871','47.177119','-1.612995');\n" +
                    "INSERT INTO 'arbre' VALUES('53','Boulevard Nelson Mandela','47.176080','-1.620683');\n" +
                    "INSERT INTO 'arbre' VALUES('54','Avenue de Beauregard','47.210366','-1.657044');\n" +
                    "INSERT INTO 'arbre' VALUES('55','Route de Pornic','47.185730','-1.600027');\n" +
                    "INSERT INTO 'arbre' VALUES('56','Rue des Pêcheurs','47.187743','-1.636367');\n" +
                    "INSERT INTO 'arbre' VALUES('57','Bourg-Solvardière','47.217103','-1.643805');\n" +
                    "INSERT INTO 'arbre' VALUES('58','D107','47.197066','-1.646078');\n" +
                    "INSERT INTO 'arbre' VALUES('59','Avenue de la Loire','47.200594','-1.668220');\n" +
                    "INSERT INTO 'arbre' VALUES('60','Rue des Vignerons','47.239689','-1.669410');\n" +
                    "INSERT INTO 'arbre' VALUES('61','Rue de la Durantière','47.213626','-1.604009');\n" +
                    "INSERT INTO 'arbre' VALUES('62','Boulevard du Tertre','47.215436','-1.605733');\n" +
                    "INSERT INTO 'arbre' VALUES('63','Rue de la Branchoire','47.218293',' -1.607099');\n" +
                    "INSERT INTO 'arbre' VALUES('64','Rue de la Chicotière','47.216539','-1.612611');\n" +
                    "INSERT INTO 'arbre' VALUES('65','Rue du Grand Taillis','47.190552','-1.513332');\n" +
                    "INSERT INTO 'arbre' VALUES('66','Rue Pessac','47.205405','-1.613002');\n" +
                    "INSERT INTO 'arbre' VALUES('67','Avenue du Suroit','47.200339','-1.609340');\n" +
                    "INSERT INTO 'arbre' VALUES('68','Boulevard Jean Moulin ','47.202875','-1.603739');\n" +
                    "INSERT INTO 'arbre' VALUES('69','Boulevard Salvador Allende','47.217098','-1.612561');\n" +
                    "INSERT INTO 'arbre' VALUES('70','Rue Romain Roland','47.205195','-1.605782');\n";

    public DbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.beginTransaction();

        sqLiteDatabase.execSQL(DROP_PARC);
        sqLiteDatabase.execSQL(DROP_ARBRE);
        for (String line : CREATE_DB.split("\n")) {
            sqLiteDatabase.execSQL(line);
        }
        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();


    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old, int newv) {
        NetworkParkList networkParkList = new NetworkParkList(context);
        networkParkList.requestParkList(new Notifier(sqLiteDatabase));
        NetworkTreeList networkTreeList = new NetworkTreeList(context);
        networkTreeList.requestTreeList(new NotifierArbres(sqLiteDatabase));
    }

    // this class allows us to be notified when data are received from the update url
    private static class Notifier implements INetworkNotifier {
        private SQLiteDatabase database;

        private Notifier(SQLiteDatabase database) {
            this.database = database;
        }

        private String createInsertParc(Parc parc) {
            return String.format("INSERT INTO %s VALUES(\"%s'\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");",
                    TABLE_PARCS, parc.getLibelle(), parc.getPosition_x(), parc.getPosition_y(), parc.isPoint_eau(), parc.isAcces_handicape(),
                    parc.isChien_interdit(), parc.getSurface(), parc.isSanitaire(), parc.isJeux(), parc.isParc_clos());
        }

        @Override
        public void dataResult(List<Parc> parcs) {
            if (parcs == null || parcs.isEmpty()) {
                return;
            }
            database.execSQL(DROP_PARC);
            database.execSQL(CREATE_PARC);
            for (Parc p : parcs) {
                database.execSQL(createInsertParc(p));
            }
        }
    }

    public class NotifierArbres implements sortiedechien.fr.retrofit_arbres.INetworkNotifier {
        private SQLiteDatabase database;

        private NotifierArbres(SQLiteDatabase database) {
            this.database = database;
        }

        @Override
        public void dataResult(List<Arbre> arbres) {
            ArbreDao arbreDao = new ArbreDao(context);
            try {
                arbreDao.open();
            } catch (IOException e) {
                return;
            }
            database.execSQL(DROP_ARBRE);
            database.execSQL(CREATE_TREE);
            for (Arbre a : arbres) {
                arbreDao.insert(a.getId(), a.getAdresse(), a.getLattitude(), a.getLongitude());
            }
            arbreDao.close();
        }
    }

}
