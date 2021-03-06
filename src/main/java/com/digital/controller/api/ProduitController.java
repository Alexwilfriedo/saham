package com.digital.controller.api;

import com.digital.model.Produit;
import com.digital.model.User;
import com.digital.model.api.ProduitApi;
import com.digital.model.response.ResponseBody;
import com.digital.model.response.RestResponse;
import com.digital.repository.ProduitRepository;
import com.digital.repository.UserRepository;
import com.digital.service.JavaMailSenderHandler;
import com.digital.service.MailTemplateHandler;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/api/v1/sahamvie")
public class ProduitController {

    @Autowired private ProduitRepository produitRepository;
    @Autowired private UserRepository userRepository ;
    @Autowired JavaMailSenderHandler javaMailSenderHandler;
    @Autowired MailTemplateHandler mailTemplateHandler;
    @RequestMapping(value = "/produits",method = RequestMethod.GET)
    public ResponseBody<List<ProduitApi>> produits(){
        Long[] codes = {7L, 43L, 53L,58L, 255L};
        List<ProduitApi> produitList = new ArrayList<>();
        ProduitApi apiproduit;
        List<Produit> produits = produitRepository.findByCodeIn(codes);
       // Document document = Jsoup
        for (Produit produit : produits){
            apiproduit =  new ProduitApi();
            apiproduit.setId(produit.getId());
            apiproduit.setCode(produit.getCode());
            apiproduit.setNomProduit(produit.getNomProduit());
            if (produit.getCode() == 7L ){
                System.out.println("produit :"+produit);
                apiproduit.setImgUrl("http://sahamassurancevie.ci/img/produits/cari.jpg");

                apiproduit.setDocument(Jsoup.parse("<div class=\"DescProdBlock\">\n" +
                        "\n" +
                        "                        <p><strong>Objectifs</strong></p>\n" +
                        "                        <p><br>\n" +
                        "                            Ce contrat permet de se  constituer ?? travers des versements  p??riodiques et/ ou libres, une ??pargne qui servira ?? financer un projet ou  disposer d&rsquo;un capital ou de revenus compl??mentaires au moment du d??part ?? la  retraite. Au terme, l&rsquo;assur?? per??oit un capital revaloris?? d&rsquo;ann??e en ann??e  gr??ce au taux d&rsquo;int??r??ts et ?? la participation aux b??n??fices distribu??s par  Sanlam Assurance-vie.</p>\n" +
                        "                        <p><br>\n" +
                        "                            <strong>Fonctionnement</strong>\n" +
                        "                        </p>\n" +
                        "                        <div class=\"content-masque\">\n" +
                        "                        <p>\n" +
                        "                            <br>\n" +
                        "                            A la souscription, l&rsquo;assur?? choisit le montant ??  ??pargner ainsi que la p??riodicit?? et le mode de paiement des primes adapt??s ??  ses projets.<br>\n" +
                        "                            <strong>L&rsquo;??pargne  est constitu??e </strong>par les cotisations nettes de frais,<strong> </strong>capitalis??e<strong> </strong>au taux d&rsquo;int??r??t technique annuel garanti de 3,5% et augment??es  des participations aux b??n??fices techniques et financiers de SANLAM Assurance  vie.<br>\n" +
                        "                            En cas de coup dur, <strong>l&rsquo;??pargne constitu??e reste accessible</strong> ??  tout moment selon les conditions pr??vues au contrat.<br>\n" +
                        "                            <strong>Au terme du contrat ou au moment  du d??part ?? la retraite, </strong>l&rsquo;assur?? per??oit l&rsquo;??pargne constitu??e sous forme de  capital ou de rentes. <br>\n" +
                        "                            <strong>En cas de d??c??s avant le terme du  contrat, </strong>l&rsquo;??pargne constitu??e au jour du d??c??s est<strong> </strong>vers??e au(x) b??n??ficiaires d??sign??s.<br><br>\n" +
                        "                            <strong>Avantages</strong><br><br>\n" +
                        "                            <strong>Libert??&nbsp;:</strong> l&rsquo;assur??  choisit librement, le montant, la dur??e, et la p??riodicit?? de ses primes.<br><br>\n" +
                        "                            <strong>Disponibilit??</strong>&nbsp;:  en cas de coup dur l&rsquo;??pargne reste disponible sous certaines conditions.<br><br>\n" +
                        "                            <strong>Rentabilit??</strong>&nbsp;:  l&rsquo;??pargne constitu??e b??n??ficie des int??r??ts et participations aux b??n??fices  distribu??s. <br><br>\n" +
                        "                            <strong>Tranquillit??</strong>&nbsp;:  la garantie et ?? la solidit?? du Groupe Sanlam donnent ?? l&rsquo;assur?? la  certitude de r??cup??rer son capital, qui servira de compl??ment de revenus ?? la  retraite.<br><br>\n" +
                        "                            <strong>Transmission</strong>&nbsp;:  gr??ce au cadre juridique et fiscal de l&rsquo;assurance-vie, le contrat CARI permet  de l??guer un capital exon??r?? de droits de succession ?? la personne de son choix.\n" +
                        "                        </p>\n" +
                        "                        <p>&nbsp;</p>\n" +
                        "                        <p>&nbsp;</p>\n" +
                        "                        <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\">\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"top\"><p><strong>TYPE </strong></p></td>\n" +
                        "                                <td  colspan=\"2\" valign=\"top\"><p>Contrat d&rsquo;Assurance Retraite Individuel </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"top\"><p><strong>OBJET </strong></p></td>\n" +
                        "                                <td colspan=\"2\" valign=\"top\"><p>Constitution et service d&rsquo;une retraite par des versements p??riodiques    ou libres </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>SOUSCRIPTEURS </strong></p></td>\n" +
                        "                                <td colspan=\"2\" valign=\"top\"><p>Personnes physiques ou morales </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>ASSURES </strong></p></td>\n" +
                        "                                <td  colspan=\"2\" valign=\"top\"><p>Personnes physiques </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>LIMITES D&rsquo;AGE A?? LA    SOUSCRIPTION </strong></p></td>\n" +
                        "                                <td colspan=\"2\" valign=\"top\"><p>Entre <strong>18 et 60 ans</strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>BENEFICIAIRES </strong></p></td>\n" +
                        "                                <td  colspan=\"2\" valign=\"top\"><p>Toute personne physique ou morale d??sign??e par le Souscripteur </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"top\"><p><strong>DUREE DU    CONTRAT </strong></p></td>\n" +
                        "                                <td  colspan=\"2\" valign=\"top\"><p><strong>10 ans au minimum </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>NATURE DES ??GARANTIES </strong></p></td>\n" +
                        "                                <td  colspan=\"2\" valign=\"top\"><p><strong>Garantie en cas de vie: </strong> <br>\n" +
                        "                                    En cas de vie de l&rsquo;Assur?? au terme du contrat, SANLAM Assurance Vie    garantit le paiement de l&rsquo;??pargne constitu??e <br>\n" +
                        "                                    <strong>Garantie en cas de d??c??s: </strong> <br>\n" +
                        "                                    En cas de D??c??s de l&rsquo;Assur?? avant le terme du contrat, SANLAM Assurance    Vie garantit le paiement de l&rsquo;??pargne constitu??e ?? la date du d??c??s </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>MONTANT DES    GARANTIES </strong></p></td>\n" +
                        "                                <td  colspan=\"2\" valign=\"top\"><p>A tout moment, l&rsquo;??pargne constitu??e est obtenue par une capitalisation    des versements nets de charges au taux d&rsquo;int??r??t technique annuel garanti de    3,5%, revaloris??e des participations aux b??n??fices techniques et financiers </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>PERIODICITE DE    PAIEMENT </strong> <br>\n" +
                        "                                    <strong>DES PRIMES </strong></p></td>\n" +
                        "                                <td width=\"558\" colspan=\"2\" valign=\"top\"><p>La p??riodicit?? de paiement des primes est fix??e par le contractant <br>\n" +
                        "                                    Elle peut ??tre mensuelle, trimestrielle, annuelle ou libre </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>MONTANT DES    PRIMES </strong></p></td>\n" +
                        "                                <td  colspan=\"2\" valign=\"top\"><p><strong>Primes p??riodiques: </strong> <br>\n" +
                        "                                    En cas de paiement p??riodique, la prime minimale autoris??e est de <strong>50.000    F/mois</strong>, <strong>150.000 F /trimestre </strong>ou <strong>600.000 F/an</strong>. Apr??s le    paiement d&rsquo;une prime annuelle, les versements deviennent totalement libres <br>\n" +
                        "                                    <strong>Versements libres: </strong> <br>\n" +
                        "                                    En cas de versements libres, le premier versement est au minimum de <strong>500.000    F </strong>et les versements suivants au minimum de <strong>250.000 F </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  rowspan=\"2\" valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>MONTANT DES    PRIMES</strong><strong><u> </u></strong></p></td>\n" +
                        "                                <td valign=\"top\"><p><strong>Rachat total</strong><strong><u> </u></strong></p></td>\n" +
                        "                                <td valign=\"top\"><p><strong>Primes p??riodiques: </strong> <br>\n" +
                        "                                    En cas de paiement p??riodique des    primes, le droit au rachat est ouvert apr??s le paiement d&rsquo;une prime annuelle <br>\n" +
                        "                                    <strong>Versements libres: </strong> <br>\n" +
                        "                                    En cas de versements libres, le    droit au rachat est ouvert imm??diatement <br>\n" +
                        "                                    Dans les deux cas de figures, la    valeur de rachat ?? reverser est ??gale au capital constitu?? diminu?? d&rsquo;une    p??nalit?? de 5% applicable sur une dur??e maximale de 10 ans<strong><u> </u></strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>Rachat partiel</strong><strong><u> </u></strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p>Rachat partiel autoris?? dans les    m??mes conditions qu&rsquo;en rachat total <br>\n" +
                        "                                    En plus les conditions suivantes    doivent ??tre respect??es <br>\n" +
                        "                                    - Montant minimum de rachat partiel    : <strong>100.000 F</strong> <br>\n" +
                        "                                    - Epargne r??siduelle minimum apr??s    rachat partiel : <strong>250.000 F</strong><strong><u> </u></strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>MODES DE    PAIEMENT DES PRIMES</strong><strong><u> </u></strong></p></td>\n" +
                        "                                <td colspan=\"2\" valign=\"top\"><p>- Pr??l??vement sur salaire (Solde des    fonctionnaires) <br>\n" +
                        "                                    - Pr??l??vement bancaire<strong><u> </u></strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                        </table>\n" +
                        "\n" +
                        "                    </div>\n" +
                        "                    </div>"));

            } else if (produit.getCode() == 53L){
                apiproduit.setImgUrl("http://sahamassurancevie.ci/img/produits/tempporaire_deces.jpg");
                apiproduit.setDocument(Jsoup.parse("<div class=\"DescProdBlock\">\n" +
                        "                     <br>\n" +
                        "                        <strong>Objectifs</strong><br><br>\n" +
                        "                            Toutes les personnes  ayant des responsabilit??s familiales, et survenant aux besoins financiers  d&rsquo;autres personnes, (conjoint, enfants, parents etc), doivent prendre des  pr??cautions pour assurer la p??rennit?? de leur action dans le cas o?? il leur  arrive un malheur<br>\n" +
                        "                            <strong>Ce  contrat est con??u pour Prot??ger</strong>, contre des difficult??s  financi??res li??es au d??c??s ou ?? l&rsquo;invalidit?? absolue et d??finitive de l&rsquo;assur??  avant le terme du contrat.<br>\n" +
                        "                            Il garantit le paiement par SANLAM Assurance Vie d&rsquo;un capital aux b??n??ficiaires d??sign??s, en cas de d??c??s de l&rsquo;assur??. Le m??me capital est vers?? ??  l&rsquo;assur?? en cas d&rsquo;invalidit?? absolue et d??finitive selon les conditions pr??vues  par le contrat. <br><br>\n" +
                        "                      <div class=\"content-masque\">\n" +
                        "                            <strong>Fonctionnement</strong>\n" +
                        "                            <br><br>\n" +
                        "                            A  la souscription, <strong>l&rsquo;assur?? choisit </strong>le  montant du capital pour lequel il sera garanti, sur les conseils de son  interm??diaire, en tenant compte de sa propre situation familiale, et de son  poids financier dans les ressources du m??nage. Souvent d??termin?? de mani??re  forfaitaire, il est fortement conseill?? de??  d??terminer le montant du capital au plus juste, (par exemple en ann??es  de salaires et /ou revenus de la personne ?? assurer).<br>\n" +
                        "                            <strong>La prime</strong> est calcul??e tenant compte du montant du capital choisi, de l&rsquo;??ge de l&rsquo;assur??,  de son ??tat de sant?? ainsi que de la dur??e du contrat. Un examen m??dical  compl??mentaire pourra ??tre demand?? en cas de besoin.<br><br>\n" +
                        "                            <strong>Avantages</strong><br><br>\n" +
                        "                            Souscrire  un contrat temporaire d??c??s invalidit?? se r??v??le utile dans bien des cas.<br>\n" +
                        "                            Le capital pay??  au(x) b??n??ficiaire(s) d??sign?? (s) permet entre autres, de pallier ou  compenser?? la disparition des revenus du  d??funt et de faire face aux besoins financiers de sa famille tels que, la  poursuite des ??tudes des enfants, la garantie d&rsquo;une vie d??cente au conjoint  survivant et aux enfants, le paiement des droits de successions.<br>\n" +
                        "                            Souscrit  en couverture d&rsquo;un engagement financier (caution, d??couvert, pr??t personnel), le <strong>contrat temporaire d??c??s</strong> permet de  d??dommager les cr??anciers en cas de d??c??s de l&rsquo;assur??.<br>\n" +
                        "                            De plus, les capitaux ne sont pas int??gr??s dans l&rsquo;assiette  de partage des biens laiss??s par le d??funt, et sont de fait, exon??r??s des  droits de successions.\n" +
                        "\n" +
                        "                            <p>&nbsp;</p>\n" +
                        "                            <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>TYPE </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Contrat d&rsquo;Assurance Individuel sur    la vie</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>OBJET </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Paiement d&rsquo;un capital, au d??c??s de    l&rsquo;assur?? ou en ca d&rsquo;invalidit?? absolue ou d??finitive </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>SOUSCRIPTEURS </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Personnes physiques ou morales </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>ASSURES </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Personnes physiques </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>BENEFICIAIRES </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Toute personne physique ou morale d??sign??e par le    Souscripteur </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>DUREE DU CONTRAT </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>De 1 ???? 10 ans renouvelables </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>LIMITES D&rsquo;AGE A?? LA SOUSCRIPTION </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Entre 12 et 64 ans </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>NATURE DES </strong> <br>\n" +
                        "                                    <strong>GARANTIES </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>D??c??s ??? Invalidit?? absolue et    d??finitive</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>MONTANT DES GARANTIES </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p><strong><u>Garantie en cas de d??c??s: </u></strong><br>\n" +
                        "                                    En cas de d??c??s de l&rsquo;Assur?? avant le terme du contrat,    paiement du capital assur??<br>\n" +
                        "                                    <strong><u>Garantie en cas d&rsquo;invalidit??    Absolue et d??finitive&nbsp;: </u></strong><u> </u><br>\n" +
                        "                                    Garantie en cas d&rsquo;invalidit?? Absolue et    d??finitive, avant le terme du contrat et au plus tard avant la fin de l&rsquo;ann??e    d&rsquo;assurance au cours de laquelle il atteint l&rsquo;??ge limite, paiement du capital    assur??</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>PERIODICITE DE    PAIEMENT DES PRIMES </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>La p??riodicit?? de paiement des    primes est annuelle<br>\n" +
                        "                                    Cependant, ces primes peuvent ??tre fractionn??es    mensuellement, trimestriellement ou semestriellement ?? condition que chaque    fraction obtenue soit de 15&nbsp;000 FCFA minimum.<br>\n" +
                        "                                    Il est alors appliqu?? les frais de fractionnement    suivants&nbsp;:</p>\n" +
                        "                                    <ul>\n" +
                        "                                        <li>Fractionnement mensuel&nbsp;: 4%</li>\n" +
                        "                                        <li>Fractionnement trimestriel&nbsp;: 3%</li>\n" +
                        "                                        <li>Fractionnement semestriel&nbsp;: 2%</li>\n" +
                        "                                    </ul></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>MONTANT DES PRIMES </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Les primes    sont d??termin??es en fonction du capital garanti, de la dur??e de paiement des    primes et de l&rsquo;??ge de l&rsquo;assur?? ?? la souscription </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>DISPONIBILITE DU CAPITAL</strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Ce contrat ne comporte ni valeur de    r??duction, ni valeur de rachat, ni avance.</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>MODES DE PAIEMENT DES    PRIMES </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>- Pr??l??vement sur salaire (Solde    des fonctionnaires) <br>\n" +
                        "                                    - Pr??l??vement bancaire </p></td>\n" +
                        "                            </tr>\n" +
                        "                        </table>\n" +
                        "                    </div>\n" +
                        "                    </div>"));
            } else if (produit.getCode() == 58){
                apiproduit.setImgUrl("http://sahamassurancevie.ci/img/produits/obseqplus.jpg");
                apiproduit.setDocument(Jsoup.parse("<div class=\"DescProdBlock\">\n" +
                        "                       <br> <strong>Objectifs</strong><br><br>\n" +
                        "                            De nos jours, l&rsquo;organisation  des fun??railles en plus d&rsquo;??tre difficile ??motionnellement entra??ne une charge  financi??re ??norme. Par OBSEQ&rsquo;PLUS, SANLAM Assurance Vie permet au souscripteur  d&rsquo;organiser et d&rsquo;anticiper ses fun??railles, ce qui aura pour effet de soulager  financi??rement ses proches.<br><br>\n" +
                        "                            <strong>Fonctionnement</strong><br><br>\n" +
                        "                            L&rsquo;assur?? choisit une formule parmi celles propos??es et verse de mani??re  p??riodique une prime sur une dur??e de 10 ans.<br>\n" +
                        "                            Lorsque le d??c??s survient, SANLAM Assurance Vie prend en charge aupr??s  d&rsquo;une pompe fun??bre les prestations pr??vues dans la formule choisie.<br>\n" +
                        "                            En plus, SANLAM Assurance Vie verse en esp??ces un capital aux  b??n??ficiaires choisis pour l&rsquo;organisation des obs??ques.<br><br>\n" +
                        "                        <div class=\"content-masque\">\n" +
                        "                            <strong>Avantages</strong><br><br>\n" +
                        "                            Cette  offre, tr??s souple, prot??ge l&rsquo;assur?? toute sa vie durant.<br>\n" +
                        "                            L&rsquo;assur??  a le choix?? entre une grande vari??t?? de  formules et peut retenir celle qui convient le plus ?? sa propre situation  personnelle. Il peut ainsi int??grer dans le contrat son conjoint, ses enfants,  ses parents, ou ceux de son conjoint.<br><br>\n" +
                        "                            <strong>Caract??ristiques</strong>\n" +
                        "\n" +
                        "                        <br>\n" +
                        "                        <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                        "                            <tr>\n" +
                        "                                <td ><br>\n" +
                        "                                    <strong>MODALITES DE PAIEMENT DES PRIMES</strong></td>\n" +
                        "                                <td ><p>Primes payables d&rsquo;avance    au si??ge social.<br>\n" +
                        "                                    R??glement en esp??ces,    ch??que, pr??l??vement bancaire et pr??l??vement sur salaire</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td ><p align=\"center\"><strong>RACHAT, AVANCE, REDUCTION</strong></p></td>\n" +
                        "                                <td ><p>Condition du rachat    total&nbsp;: paiement de deux ann??es de prime ou de 15% des primes du    contrat.<br>\n" +
                        "                                    Les rachats partiels,    l&rsquo;octroie d&rsquo;avance et la r??duction des garanties ne sont pas autoris??s.</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td ><p align=\"center\"><strong>RISQUES NON COUVERTS</strong></p></td>\n" +
                        "                                <td ><ul>\n" +
                        "                                    <li>Le    suicide ou tentative de suicide de l&rsquo;assur?? au cours des 2 premi??res ann??es</li>\n" +
                        "                                    <li>Le    meurtre de l&rsquo;assur?? par le b??n??ficiaire</li>\n" +
                        "                                    <li>Le    risque de guerre ??trang??re</li>\n" +
                        "                                    <li>L&rsquo;usage    de drogues ou de stup??fiants</li>\n" +
                        "                                    <li>Une    maladie ant??rieure ?? la date de souscription </li>\n" +
                        "                                </ul></td>\n" +
                        "                            </tr>\n" +
                        "                        </table>\n" +
                        "                        <p>&nbsp;</p>\n" +
                        "                        <p>&nbsp;</p><br>\n" +
                        "                        <p><strong>FORMULE INDIVIDUELLE</strong></p><br>\n" +
                        "                        <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" >\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><br>\n" +
                        "                                    <strong>Garanties de base </strong><strong> </strong></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Dignit?? </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Qui??tude </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>S??r??nit?? </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Confort </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Prestige </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Rapatriement du corps </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Non </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Non </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Non </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Non </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Dans la limite de    2&nbsp;000&nbsp;000 FCFA </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Transport du d??funt </strong><strong> </strong></p></td>\n" +
                        "                                <td  colspan=\"5\" valign=\"bottom\"><p><strong>Jusqu'?? IVOSEP ou ?? une    autre pompe fun??bre si IVOSEP non pr??sent </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Traitement du corps </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Non </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Oui </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Oui </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Oui </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Oui </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Conservation du corps </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Maxi 72H </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>7 jours </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>15 jours </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>30 jours </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>30 jours </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"top\"><p><strong>Lev??e du corps </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle A </strong><strong> </strong><br>\n" +
                        "                                    <strong>IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle A IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Pr??au IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle B ou pr??au?? IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle B ou pr??au?? IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Allocation corbillard et    achat cercueil </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:???????????? 80 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:150 000</strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:300 000</strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:500 000</strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:1000 000</strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Frais en esp??ces</strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte: 200 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:200 000</strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:300 000</strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:500 000</strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:1000 000</strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                        </table><br>\n" +
                        "                        <p><strong>FORMULE FAMILIALE</strong></p><br>\n" +
                        "                        <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\"  >\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><br>\n" +
                        "                                    <strong>Garanties de base </strong><strong> </strong></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Dignit?? </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Qui??tude </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>S??r??nit?? </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Confort </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Prestige </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Rapatriement du corps </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Non </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Non </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Non </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Non </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Dans la limite de 2&nbsp;000&nbsp;000 FCFA </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Transport du d??funt </strong><strong> </strong></p></td>\n" +
                        "                                <td width=\"548\" colspan=\"5\" valign=\"bottom\"><p><strong>Jusqu'?? IVOSEP ou ?? une autre pompe fun??bre si    IVOSEP non pr??sent </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Traitement du corps </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Non </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Oui </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Oui </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Oui </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Oui </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Conservation du corps </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Maxi 72H </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>7 jours </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>15 jours </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>30 jours </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>30 jours </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"top\"><p><strong>Lev??e du corps </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle A </strong><strong> </strong><br>\n" +
                        "                                    <strong>IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle A IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Pr??au IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle B ou pr??au??    IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle B ou pr??au??    IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Allocation corbillard et achat cercueil </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte: 80 000 <br>\n" +
                        "                                    Enfant: 50 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:150 000</strong><strong> </strong><br>\n" +
                        "                                    <strong>Enfant:?? 80    000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:300 000</strong><strong> </strong><br>\n" +
                        "                                    <strong>Enfant:200 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:500 000</strong><strong> </strong><br>\n" +
                        "                                    <strong>Enfant:200 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:1000 000</strong><strong> </strong><br>\n" +
                        "                                    <strong>Enfant: 500 000 </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Frais en esp??ces </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte: 200 000 <br>\n" +
                        "                                    Enfant: 100 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:200 000</strong><strong> </strong><br>\n" +
                        "                                    <strong>Enfant:100 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:300 000</strong><strong> </strong><br>\n" +
                        "                                    <strong>Enfant:200 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:500 000</strong><strong> </strong><br>\n" +
                        "                                    <strong>Enfant:200 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:1000 000</strong><strong> </strong><br>\n" +
                        "                                    <strong>Enfant: 500 000 </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                        </table><br>\n" +
                        "                        <p><strong>FORMULE COMPLEMENTAIRE  ASCENDANT</strong></p><br>\n" +
                        "                        <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" width=\"694\">\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"165\" valign=\"bottom\"><br>\n" +
                        "                                    <strong>Options </strong><strong> </strong></td>\n" +
                        "                                <td width=\"85\" valign=\"bottom\"><p><strong>Dignit?? </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Qui??tude </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>S??r??nit?? </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Confort </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Prestige </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"165\" valign=\"bottom\"><p><strong>Capitaux garantis </strong><strong> </strong></p></td>\n" +
                        "                                <td width=\"85\" valign=\"bottom\"><p><strong>300 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>500 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>700 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>1 000 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>1 500 000 </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                        </table>\n" +
                        "\n" +
                        "                    </div>\n" +
                        "                    </div>"));
            }else if (produit.getCode() == 255){
                apiproduit.setImgUrl("http://sahamassurancevie.ci/img/produits/educplus.jpg");
                apiproduit.setDocument(Jsoup.parse("<div class=\"DescProdBlock\">\n" +
                        "                        <p>\n" +
                        "                            <strong>Objectifs</strong><br><br>\n" +
                        "                            Con??u en vue de  garantir les moyens financiers n??cessaires ?? l&rsquo;??ducation des enfants, la Rente  Education permet, par des versements p??riodiques, la constitution et le service  d&rsquo;une rente certaine:<br><br>\n" +
                        "                            <strong>Fonctionnement</strong><br><br>\n" +
                        "                            <strong>Le client choisit</strong> le  montant de la rente qu&rsquo;il souhaite percevoir annuellement. La prime<strong> </strong>??est calcul??e en tenant compte de la rente  choisie, de l&rsquo;??ge de l&rsquo;assur?? ainsi que de la dur??e du contrat.<br>\n" +
                        "                            <strong>Le capital</strong> constitutif de la  rente est revaloris?? des participations aux b??n??fices annuelles de SANLAM  Assurance Vie, et reste accessible en cas de changement d&rsquo;objectif, selon les  termes du contrat.<strong></strong><br>\n" +
                        "                            <strong>Au </strong>terme du contrat si l&rsquo;assur?? est en vie, il  per??oit?? la rente annuelle promise  pendant 6 (six) ans.\n" +
                        "                        </p>\n" +
                        "                        <div class=\"content-masque\">\n" +
                        "                        <p>\n" +
                        "                            <strong>En  cas de D??c??s de l&rsquo;Assur??</strong> avant le  terme de la p??riode de paiement des primes, le(s ) b??n??ficiaire(s) d??sign??(s)  per??oit (vent) ?? chaque date anniversaire du contrat, la rente annuelle  certaine souscrite, sur une dur??e partant de la date du d??c??s ?? la fin de la  p??riode pr??vue pour le paiement des rentes en cas de vie. <br><br>\n" +
                        "                            <strong>Avantages</strong><br><br>\n" +
                        "                            <strong>L&rsquo;assur??</strong> (e) pr??pare l&rsquo;avenir de ses enfants avec certitude  et tranquillit??. En effet, qu&rsquo;il soit lui-m??me vivant ou non au terme qu&rsquo;il a  choisi pour le contrat, la rente certaine pr??vue est vers??e par SANLAM  Assurance-Vie. <br>\n" +
                        "                            La rente vers??e en cas de au terme du contrat  Educ&rsquo;plus peut ??galement servir ?? financer un projet, ou faire face aux  besoins?? ponctuels, comme lors du d??part  ?? la retraite par exemple.<br>\n" +
                        "                            Si ses projets changent en cours de route, il garde  la possibilit?? ??d&rsquo;acc??der?? ?? la part d&rsquo;??pargne d??termin??e par la valeur  de rachat de son contrat.<br><br>\n" +
                        "                            <strong>Sch??matisation</strong>\n" +
                        "                        </p>\n" +
                        "                        <br>\n" +
                        "                        <p> <img src=\"../img/produits/schematisations.jpg\" /></p>\n" +
                        "                        <br>\n" +
                        "                        <p><strong>Caract??ristiques</strong></p>\n" +
                        "                        <br>\n" +
                        "                        <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" width=\"652\">\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><h4>&nbsp;</h4>\n" +
                        "                                    <h4>TYPE </h4></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p>Contrat    d&rsquo;Assurance Vie de type mixte</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>OBJET</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>Constitution    et service d&rsquo;une rente par des versements p??riodiques, en vue de l&rsquo;??ducation    des enfants</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>CONTRACTANT - SOUSCRIPTEUR?? </strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p>Personnes    physiques, (titulaires d&rsquo;un compte courant ou d&rsquo;??pargne)</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>ASSURES?????????????????????????? </strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>Personnes    physiques </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>LIMITES D&rsquo;AGE A ??LA SOUSCRIPTION </strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>Minimum&nbsp;: 18    ans&nbsp;; maximum&nbsp;: 60 ans</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>BENEFICIAIRES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p>Toute    personne physique d??sign??e par le Souscripteur</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>DUREE DE PAIEMENT DES COTISATIONS </strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p>Minimum 5 ans&nbsp;: maximum 18 ans</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>NATURE DES GARANTIES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>Vie ???    D??c??s ??? Invalidit?? Absolue et D??finitive</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>LES GARANTIES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p><strong><u>Garantie en cas de vie</u></strong><strong>:</strong><br>\n" +
                        "                                    En cas de vie de l&rsquo;Assur??    au terme du contrat, paiement pendant 6 ans de la rente annuelle certaine?? souscrite<br>\n" +
                        "                                    <strong><u>Garantie en cas de d??c??s</u></strong><strong>:</strong><br>\n" +
                        "                                    En cas de D??c??s de    l&rsquo;Assur?? avant le terme de la p??riode de paiement des primes, paiement, ??    chaque date anniversaire du contrat, de la rente annuelle certaine souscrite,    sur une dur??e partant du d??c??s ?? la fin de la p??riode pr??vue pour le paiement    des rentes en cas de vie. Application d&rsquo;un prorata au premier terme de rente.<br>\n" +
                        "                                    <strong><u>Garantie en cas d&rsquo;Invalidit?? Absolue et D??finitive</u></strong><strong>:</strong><br>\n" +
                        "                                    En cas d&rsquo;Invalidit??    Absolue et D??finitive de l&rsquo;Assur?? avant le terme de la p??riode de paiement    des primes, paiement, ?? chaque date anniversaire du contrat, de la rente    annuelle certaine souscrite jusqu&rsquo;?? la fin de la p??riode pr??vue pour le    paiement des rentes en cas de vie. </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>MONTANT MINIMUM DES RENTES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p>Rente annuelle minimum    admissible&nbsp;: 100.000 F CFA </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>PERIODICITE DE PAIEMENT DES PRIMES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p>Elle    est choisie par le contractant&nbsp;; elle peut ??tre mensuelle,    trimestrielle, semestrielle?? ou    annuelle.</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>MONTANT DES PRIMES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p><strong><u>&nbsp;</u></strong></p>\n" +
                        "                                    <p>Les primes sont    d??termin??es en fonction de la rente annuelle souscrite, de la dur??e de    cotisations et de l&rsquo;??ge de l&rsquo;assur?? ?? la souscription. Voir l&rsquo;extrait    tarifaire.</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" rowspan=\"3\" valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>DISPONIBILITE DU CAPITAL</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p></td>\n" +
                        "                                <td width=\"113\" valign=\"top\"><p><strong>Rachat total</strong></p></td>\n" +
                        "                                <td width=\"397\" valign=\"top\"><p>Le droit au rachat    est ouvert apr??s le paiement de deux (02) primes annuelles ou de 15% des    primes pr??vues au contrat.<br>\n" +
                        "                                    La valeur de    rachat est alors ??gale ?? la provision math??matique du contrat diminu??e d&rsquo;une    p??nalit?? de 5% applicable sur une dur??e maximale de 10 ans.</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"113\" valign=\"top\"><p><strong>Rachat partiel</strong></p></td>\n" +
                        "                                <td width=\"397\" valign=\"top\"><p>Ce contrat n&rsquo;autorise pas de rachats    partiels. </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"113\" valign=\"top\"><p><strong>Avance</strong></p></td>\n" +
                        "                                <td width=\"397\" valign=\"top\"><p>Elle est possible apr??s le paiement    effectif de deux (02) primes annuelles.<br>\n" +
                        "                                    Montants maximum&nbsp;: 95% de la valeur    de rachat, <br>\n" +
                        "                                    Montant minimum&nbsp;: 150&nbsp;000 F cfa</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>MODES DE PAIEMENT DES PRIMES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p>??Pr??l??vement    bancaire </p></td>\n" +
                        "                            </tr>\n" +
                        "                        </table>\n" +
                        "                    </div>\n" +
                        "                    </div>"));
            } else if (produit.getCode() ==43L){
                apiproduit.setImgUrl("http://sahamassurancevie.ci/img/produits/capinvest.jpg");
                apiproduit.setDocument(Jsoup.parse(""));
            }
           // System.out.println("apiproduit doc :"+apiproduit.getDocument().outerHtml());
            produitList.add(apiproduit);
        }
        return new ResponseBody<List<ProduitApi>>(produitList, new RestResponse(false,"les produits par branche"));
    }

/*    @RequestMapping(value = "/detail-produit",method = RequestMethod.POST)
    public HashMap<String,Object> detailProduit(String produit,String nom, String tel,String email){
        HashMap<String, Object> data = new HashMap<>();
        System.out.println("produit "+produit);
        System.out.println("nom "+nom);
        System.out.println("tel "+tel);
        System.out.println("email "+email);
      *//*  try {
            javaMailSenderHandler.send("info.vie.ci@sahamassurance.com","demande de detail produit "+produit,"demande de detail formul?? par "+nom+" dont le numero de telephone est :"+tel+"et l'adresse email est:"+email+"",false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }*//*
        data.put("succes",true);
        data.put("message", "merci pour votre interessement ,vous serez contact??");
        return data;
    }*/

    @PostMapping(value = "/getDetail/product")
    ResponseBody<User> getDetailProduct(
            @RequestParam String username,
            @RequestParam String product,
            HttpServletRequest request) throws ExecutionException, InterruptedException {
        RestResponse restResponse = new RestResponse();
        User user =userRepository.findFirstByUsername(username);
        if (user !=null){
        try {
            Context context = new Context();
            context.setVariable("nom",user.getLastname() +" "+user.getFirstname());
            context.setVariable("tel",user.getPhone());
            context.setVariable("email",user.getEmail());
            context.setVariable("produit",product);
            Future<String> content = mailTemplateHandler.build("mailTemplates/demandeInformation",context);
            javaMailSenderHandler.send("info.vie.ci@sahamassurance.com","demande de detail produit "+product,content.get().toString(),true);
            restResponse.setError(false);
            restResponse.setMessage("merci pour votre interessement ,vous serez contact??");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        }else {
            restResponse.setError(true);
            restResponse.setMessage("Aucun utilisateur trouv??");
        }
            return new ResponseBody<User>(user, restResponse);
    }
}
