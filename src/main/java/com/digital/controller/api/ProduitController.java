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
                        "                            Ce contrat permet de se  constituer à travers des versements  périodiques et/ ou libres, une épargne qui servira à financer un projet ou  disposer d&rsquo;un capital ou de revenus complémentaires au moment du départ à la  retraite. Au terme, l&rsquo;assuré perçoit un capital revalorisé d&rsquo;année en année  grâce au taux d&rsquo;intérêts et à la participation aux bénéfices distribués par  Saham Assurance-vie.</p>\n" +
                        "                        <p><br>\n" +
                        "                            <strong>Fonctionnement</strong>\n" +
                        "                        </p>\n" +
                        "                        <div class=\"content-masque\">\n" +
                        "                        <p>\n" +
                        "                            <br>\n" +
                        "                            A la souscription, l&rsquo;assuré choisit le montant à  épargner ainsi que la périodicité et le mode de paiement des primes adaptés à  ses projets.<br>\n" +
                        "                            <strong>L&rsquo;épargne  est constituée </strong>par les cotisations nettes de frais,<strong> </strong>capitalisée<strong> </strong>au taux d&rsquo;intérêt technique annuel garanti de 3,5% et augmentées  des participations aux bénéfices techniques et financiers de SAHAM Assurance  vie.<br>\n" +
                        "                            En cas de coup dur, <strong>l&rsquo;épargne constituée reste accessible</strong> à  tout moment selon les conditions prévues au contrat.<br>\n" +
                        "                            <strong>Au terme du contrat ou au moment  du départ à la retraite, </strong>l&rsquo;assuré perçoit l&rsquo;épargne constituée sous forme de  capital ou de rentes. <br>\n" +
                        "                            <strong>En cas de décès avant le terme du  contrat, </strong>l&rsquo;épargne constituée au jour du décès est<strong> </strong>versée au(x) bénéficiaires désignés.<br><br>\n" +
                        "                            <strong>Avantages</strong><br><br>\n" +
                        "                            <strong>Liberté&nbsp;:</strong> l&rsquo;assuré  choisit librement, le montant, la durée, et la périodicité de ses primes.<br><br>\n" +
                        "                            <strong>Disponibilité</strong>&nbsp;:  en cas de coup dur l&rsquo;épargne reste disponible sous certaines conditions.<br><br>\n" +
                        "                            <strong>Rentabilité</strong>&nbsp;:  l&rsquo;épargne constituée bénéficie des intérêts et participations aux bénéfices  distribués. <br><br>\n" +
                        "                            <strong>Tranquillité</strong>&nbsp;:  la garantie et à la solidité du Groupe Saham donnent à l&rsquo;assuré la  certitude de récupérer son capital, qui servira de complément de revenus à la  retraite.<br><br>\n" +
                        "                            <strong>Transmission</strong>&nbsp;:  grâce au cadre juridique et fiscal de l&rsquo;assurance-vie, le contrat CARI permet  de léguer un capital exonéré de droits de succession à la personne de son choix.\n" +
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
                        "                                <td colspan=\"2\" valign=\"top\"><p>Constitution et service d&rsquo;une retraite par des versements périodiques    ou libres </p></td>\n" +
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
                        "                                <td valign=\"top\"><p><strong>LIMITES D&rsquo;AGE A  LA    SOUSCRIPTION </strong></p></td>\n" +
                        "                                <td colspan=\"2\" valign=\"top\"><p>Entre <strong>18 et 60 ans</strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>BENEFICIAIRES </strong></p></td>\n" +
                        "                                <td  colspan=\"2\" valign=\"top\"><p>Toute personne physique ou morale désignée par le Souscripteur </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"top\"><p><strong>DUREE DU    CONTRAT </strong></p></td>\n" +
                        "                                <td  colspan=\"2\" valign=\"top\"><p><strong>10 ans au minimum </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>NATURE DES  GARANTIES </strong></p></td>\n" +
                        "                                <td  colspan=\"2\" valign=\"top\"><p><strong>Garantie en cas de vie: </strong> <br>\n" +
                        "                                    En cas de vie de l&rsquo;Assuré au terme du contrat, SAHAM Assurance Vie    garantit le paiement de l&rsquo;épargne constituée <br>\n" +
                        "                                    <strong>Garantie en cas de décès: </strong> <br>\n" +
                        "                                    En cas de Décès de l&rsquo;Assuré avant le terme du contrat, SAHAM Assurance    Vie garantit le paiement de l&rsquo;épargne constituée à la date du décès </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>MONTANT DES    GARANTIES </strong></p></td>\n" +
                        "                                <td  colspan=\"2\" valign=\"top\"><p>A tout moment, l&rsquo;épargne constituée est obtenue par une capitalisation    des versements nets de charges au taux d&rsquo;intérêt technique annuel garanti de    3,5%, revalorisée des participations aux bénéfices techniques et financiers </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>PERIODICITE DE    PAIEMENT </strong> <br>\n" +
                        "                                    <strong>DES PRIMES </strong></p></td>\n" +
                        "                                <td width=\"558\" colspan=\"2\" valign=\"top\"><p>La périodicité de paiement des primes est fixée par le contractant <br>\n" +
                        "                                    Elle peut être mensuelle, trimestrielle, annuelle ou libre </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>MONTANT DES    PRIMES </strong></p></td>\n" +
                        "                                <td  colspan=\"2\" valign=\"top\"><p><strong>Primes périodiques: </strong> <br>\n" +
                        "                                    En cas de paiement périodique, la prime minimale autorisée est de <strong>50.000    F/mois</strong>, <strong>150.000 F /trimestre </strong>ou <strong>600.000 F/an</strong>. Après le    paiement d&rsquo;une prime annuelle, les versements deviennent totalement libres <br>\n" +
                        "                                    <strong>Versements libres: </strong> <br>\n" +
                        "                                    En cas de versements libres, le premier versement est au minimum de <strong>500.000    F </strong>et les versements suivants au minimum de <strong>250.000 F </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  rowspan=\"2\" valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>MONTANT DES    PRIMES</strong><strong><u> </u></strong></p></td>\n" +
                        "                                <td valign=\"top\"><p><strong>Rachat total</strong><strong><u> </u></strong></p></td>\n" +
                        "                                <td valign=\"top\"><p><strong>Primes périodiques: </strong> <br>\n" +
                        "                                    En cas de paiement périodique des    primes, le droit au rachat est ouvert après le paiement d&rsquo;une prime annuelle <br>\n" +
                        "                                    <strong>Versements libres: </strong> <br>\n" +
                        "                                    En cas de versements libres, le    droit au rachat est ouvert immédiatement <br>\n" +
                        "                                    Dans les deux cas de figures, la    valeur de rachat à reverser est égale au capital constitué diminué d&rsquo;une    pénalité de 5% applicable sur une durée maximale de 10 ans<strong><u> </u></strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>Rachat partiel</strong><strong><u> </u></strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p>Rachat partiel autorisé dans les    mêmes conditions qu&rsquo;en rachat total <br>\n" +
                        "                                    En plus les conditions suivantes    doivent être respectées <br>\n" +
                        "                                    - Montant minimum de rachat partiel    : <strong>100.000 F</strong> <br>\n" +
                        "                                    - Epargne résiduelle minimum après    rachat partiel : <strong>250.000 F</strong><strong><u> </u></strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>MODES DE    PAIEMENT DES PRIMES</strong><strong><u> </u></strong></p></td>\n" +
                        "                                <td colspan=\"2\" valign=\"top\"><p>- Prélèvement sur salaire (Solde des    fonctionnaires) <br>\n" +
                        "                                    - Prélèvement bancaire<strong><u> </u></strong></p></td>\n" +
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
                        "                            Toutes les personnes  ayant des responsabilités familiales, et survenant aux besoins financiers  d&rsquo;autres personnes, (conjoint, enfants, parents etc), doivent prendre des  précautions pour assurer la pérennité de leur action dans le cas où il leur  arrive un malheur<br>\n" +
                        "                            <strong>Ce  contrat est conçu pour Protéger</strong>, contre des difficultés  financières liées au décès ou à l&rsquo;invalidité absolue et définitive de l&rsquo;assuré  avant le terme du contrat.<br>\n" +
                        "                            Il garantit le paiement par SAHAM Assurance Vie d&rsquo;un capital aux bénéficiaires désignés, en cas de décès de l&rsquo;assuré. Le même capital est versé à  l&rsquo;assuré en cas d&rsquo;invalidité absolue et définitive selon les conditions prévues  par le contrat. <br><br>\n" +
                        "                      <div class=\"content-masque\">\n" +
                        "                            <strong>Fonctionnement</strong>\n" +
                        "                            <br><br>\n" +
                        "                            A  la souscription, <strong>l&rsquo;assuré choisit </strong>le  montant du capital pour lequel il sera garanti, sur les conseils de son  intermédiaire, en tenant compte de sa propre situation familiale, et de son  poids financier dans les ressources du ménage. Souvent déterminé de manière  forfaitaire, il est fortement conseillé de   déterminer le montant du capital au plus juste, (par exemple en années  de salaires et /ou revenus de la personne à assurer).<br>\n" +
                        "                            <strong>La prime</strong> est calculée tenant compte du montant du capital choisi, de l&rsquo;âge de l&rsquo;assuré,  de son état de santé ainsi que de la durée du contrat. Un examen médical  complémentaire pourra être demandé en cas de besoin.<br><br>\n" +
                        "                            <strong>Avantages</strong><br><br>\n" +
                        "                            Souscrire  un contrat temporaire décès invalidité se révèle utile dans bien des cas.<br>\n" +
                        "                            Le capital payé  au(x) bénéficiaire(s) désigné (s) permet entre autres, de pallier ou  compenser  la disparition des revenus du  défunt et de faire face aux besoins financiers de sa famille tels que, la  poursuite des études des enfants, la garantie d&rsquo;une vie décente au conjoint  survivant et aux enfants, le paiement des droits de successions.<br>\n" +
                        "                            Souscrit  en couverture d&rsquo;un engagement financier (caution, découvert, prêt personnel), le <strong>contrat temporaire décès</strong> permet de  dédommager les créanciers en cas de décès de l&rsquo;assuré.<br>\n" +
                        "                            De plus, les capitaux ne sont pas intégrés dans l&rsquo;assiette  de partage des biens laissés par le défunt, et sont de fait, exonérés des  droits de successions.\n" +
                        "\n" +
                        "                            <p>&nbsp;</p>\n" +
                        "                            <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>TYPE </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Contrat d&rsquo;Assurance Individuel sur    la vie</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>OBJET </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Paiement d&rsquo;un capital, au décès de    l&rsquo;assuré ou en ca d&rsquo;invalidité absolue ou définitive </p></td>\n" +
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
                        "                                <td valign=\"top\"><p>Toute personne physique ou morale désignée par le    Souscripteur </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>DUREE DU CONTRAT </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>De 1 à  10 ans renouvelables </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>LIMITES D&rsquo;AGE A  LA SOUSCRIPTION </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Entre 12 et 64 ans </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>NATURE DES </strong> <br>\n" +
                        "                                    <strong>GARANTIES </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Décès – Invalidité absolue et    définitive</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>MONTANT DES GARANTIES </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p><strong><u>Garantie en cas de décès: </u></strong><br>\n" +
                        "                                    En cas de décès de l&rsquo;Assuré avant le terme du contrat,    paiement du capital assuré<br>\n" +
                        "                                    <strong><u>Garantie en cas d&rsquo;invalidité    Absolue et définitive&nbsp;: </u></strong><u> </u><br>\n" +
                        "                                    Garantie en cas d&rsquo;invalidité Absolue et    définitive, avant le terme du contrat et au plus tard avant la fin de l&rsquo;année    d&rsquo;assurance au cours de laquelle il atteint l&rsquo;âge limite, paiement du capital    assuré</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>PERIODICITE DE    PAIEMENT DES PRIMES </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>La périodicité de paiement des    primes est annuelle<br>\n" +
                        "                                    Cependant, ces primes peuvent être fractionnées    mensuellement, trimestriellement ou semestriellement à condition que chaque    fraction obtenue soit de 15&nbsp;000 FCFA minimum.<br>\n" +
                        "                                    Il est alors appliqué les frais de fractionnement    suivants&nbsp;:</p>\n" +
                        "                                    <ul>\n" +
                        "                                        <li>Fractionnement mensuel&nbsp;: 4%</li>\n" +
                        "                                        <li>Fractionnement trimestriel&nbsp;: 3%</li>\n" +
                        "                                        <li>Fractionnement semestriel&nbsp;: 2%</li>\n" +
                        "                                    </ul></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>MONTANT DES PRIMES </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Les primes    sont déterminées en fonction du capital garanti, de la durée de paiement des    primes et de l&rsquo;âge de l&rsquo;assuré à la souscription </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>DISPONIBILITE DU CAPITAL</strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>Ce contrat ne comporte ni valeur de    réduction, ni valeur de rachat, ni avance.</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\"><p><strong>MODES DE PAIEMENT DES    PRIMES </strong></p></td>\n" +
                        "                                <td valign=\"top\"><p>- Prélèvement sur salaire (Solde    des fonctionnaires) <br>\n" +
                        "                                    - Prélèvement bancaire </p></td>\n" +
                        "                            </tr>\n" +
                        "                        </table>\n" +
                        "                    </div>\n" +
                        "                    </div>"));
            } else if (produit.getCode() == 58){
                apiproduit.setImgUrl("http://sahamassurancevie.ci/img/produits/obseqplus.jpg");
                apiproduit.setDocument(Jsoup.parse("<div class=\"DescProdBlock\">\n" +
                        "                       <br> <strong>Objectifs</strong><br><br>\n" +
                        "                            De nos jours, l&rsquo;organisation  des funérailles en plus d&rsquo;être difficile émotionnellement entraîne une charge  financière énorme. Par OBSEQ&rsquo;PLUS, SAHAM Assurance Vie permet au souscripteur  d&rsquo;organiser et d&rsquo;anticiper ses funérailles, ce qui aura pour effet de soulager  financièrement ses proches.<br><br>\n" +
                        "                            <strong>Fonctionnement</strong><br><br>\n" +
                        "                            L&rsquo;assuré choisit une formule parmi celles proposées et verse de manière  périodique une prime sur une durée de 10 ans.<br>\n" +
                        "                            Lorsque le décès survient, SAHAM Assurance Vie prend en charge auprès  d&rsquo;une pompe funèbre les prestations prévues dans la formule choisie.<br>\n" +
                        "                            En plus, SAHAM Assurance Vie verse en espèces un capital aux  bénéficiaires choisis pour l&rsquo;organisation des obsèques.<br><br>\n" +
                        "                        <div class=\"content-masque\">\n" +
                        "                            <strong>Avantages</strong><br><br>\n" +
                        "                            Cette  offre, très souple, protège l&rsquo;assuré toute sa vie durant.<br>\n" +
                        "                            L&rsquo;assuré  a le choix  entre une grande variété de  formules et peut retenir celle qui convient le plus à sa propre situation  personnelle. Il peut ainsi intégrer dans le contrat son conjoint, ses enfants,  ses parents, ou ceux de son conjoint.<br><br>\n" +
                        "                            <strong>Caractéristiques</strong>\n" +
                        "\n" +
                        "                        <br>\n" +
                        "                        <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                        "                            <tr>\n" +
                        "                                <td ><br>\n" +
                        "                                    <strong>MODALITES DE PAIEMENT DES PRIMES</strong></td>\n" +
                        "                                <td ><p>Primes payables d&rsquo;avance    au siège social.<br>\n" +
                        "                                    Règlement en espèces,    chèque, prélèvement bancaire et prélèvement sur salaire</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td ><p align=\"center\"><strong>RACHAT, AVANCE, REDUCTION</strong></p></td>\n" +
                        "                                <td ><p>Condition du rachat    total&nbsp;: paiement de deux années de prime ou de 15% des primes du    contrat.<br>\n" +
                        "                                    Les rachats partiels,    l&rsquo;octroie d&rsquo;avance et la réduction des garanties ne sont pas autorisés.</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td ><p align=\"center\"><strong>RISQUES NON COUVERTS</strong></p></td>\n" +
                        "                                <td ><ul>\n" +
                        "                                    <li>Le    suicide ou tentative de suicide de l&rsquo;assuré au cours des 2 premières années</li>\n" +
                        "                                    <li>Le    meurtre de l&rsquo;assuré par le bénéficiaire</li>\n" +
                        "                                    <li>Le    risque de guerre étrangère</li>\n" +
                        "                                    <li>L&rsquo;usage    de drogues ou de stupéfiants</li>\n" +
                        "                                    <li>Une    maladie antérieure à la date de souscription </li>\n" +
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
                        "                                <td  valign=\"bottom\"><p><strong>Dignité </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Quiétude </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Sérénité </strong><strong> </strong></p></td>\n" +
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
                        "                                <td  valign=\"bottom\"><p><strong>Transport du défunt </strong><strong> </strong></p></td>\n" +
                        "                                <td  colspan=\"5\" valign=\"bottom\"><p><strong>Jusqu'à IVOSEP ou à une    autre pompe funèbre si IVOSEP non présent </strong><strong> </strong></p></td>\n" +
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
                        "                                <td  valign=\"top\"><p><strong>Levée du corps </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle A </strong><strong> </strong><br>\n" +
                        "                                    <strong>IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle A IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Préau IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle B ou préau  IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle B ou préau  IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Allocation corbillard et    achat cercueil </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:       80 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:150 000</strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:300 000</strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:500 000</strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:1000 000</strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Frais en espèces</strong><strong> </strong></p></td>\n" +
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
                        "                                <td  valign=\"bottom\"><p><strong>Dignité </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Quiétude </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Sérénité </strong><strong> </strong></p></td>\n" +
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
                        "                                <td  valign=\"bottom\"><p><strong>Transport du défunt </strong><strong> </strong></p></td>\n" +
                        "                                <td width=\"548\" colspan=\"5\" valign=\"bottom\"><p><strong>Jusqu'à IVOSEP ou à une autre pompe funèbre si    IVOSEP non présent </strong><strong> </strong></p></td>\n" +
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
                        "                                <td  valign=\"top\"><p><strong>Levée du corps </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle A </strong><strong> </strong><br>\n" +
                        "                                    <strong>IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle A IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Préau IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle B ou préau     IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"top\"><p><strong>Salle B ou préau     IVOSEP </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Allocation corbillard et achat cercueil </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte: 80 000 <br>\n" +
                        "                                    Enfant: 50 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:150 000</strong><strong> </strong><br>\n" +
                        "                                    <strong>Enfant:  80    000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:300 000</strong><strong> </strong><br>\n" +
                        "                                    <strong>Enfant:200 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:500 000</strong><strong> </strong><br>\n" +
                        "                                    <strong>Enfant:200 000 </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Adulte:1000 000</strong><strong> </strong><br>\n" +
                        "                                    <strong>Enfant: 500 000 </strong><strong> </strong></p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Frais en espèces </strong><strong> </strong></p></td>\n" +
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
                        "                                <td width=\"85\" valign=\"bottom\"><p><strong>Dignité </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Quiétude </strong><strong> </strong></p></td>\n" +
                        "                                <td  valign=\"bottom\"><p><strong>Sérénité </strong><strong> </strong></p></td>\n" +
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
                        "                            Conçu en vue de  garantir les moyens financiers nécessaires à l&rsquo;éducation des enfants, la Rente  Education permet, par des versements périodiques, la constitution et le service  d&rsquo;une rente certaine:<br><br>\n" +
                        "                            <strong>Fonctionnement</strong><br><br>\n" +
                        "                            <strong>Le client choisit</strong> le  montant de la rente qu&rsquo;il souhaite percevoir annuellement. La prime<strong> </strong> est calculée en tenant compte de la rente  choisie, de l&rsquo;âge de l&rsquo;assuré ainsi que de la durée du contrat.<br>\n" +
                        "                            <strong>Le capital</strong> constitutif de la  rente est revalorisé des participations aux bénéfices annuelles de SAHAM  Assurance Vie, et reste accessible en cas de changement d&rsquo;objectif, selon les  termes du contrat.<strong></strong><br>\n" +
                        "                            <strong>Au </strong>terme du contrat si l&rsquo;assuré est en vie, il  perçoit  la rente annuelle promise  pendant 6 (six) ans.\n" +
                        "                        </p>\n" +
                        "                        <div class=\"content-masque\">\n" +
                        "                        <p>\n" +
                        "                            <strong>En  cas de Décès de l&rsquo;Assuré</strong> avant le  terme de la période de paiement des primes, le(s ) bénéficiaire(s) désigné(s)  perçoit (vent) à chaque date anniversaire du contrat, la rente annuelle  certaine souscrite, sur une durée partant de la date du décès à la fin de la  période prévue pour le paiement des rentes en cas de vie. <br><br>\n" +
                        "                            <strong>Avantages</strong><br><br>\n" +
                        "                            <strong>L&rsquo;assuré</strong> (e) prépare l&rsquo;avenir de ses enfants avec certitude  et tranquillité. En effet, qu&rsquo;il soit lui-même vivant ou non au terme qu&rsquo;il a  choisi pour le contrat, la rente certaine prévue est versée par SAHAM  Assurance-Vie. <br>\n" +
                        "                            La rente versée en cas de au terme du contrat  Educ&rsquo;plus peut également servir à financer un projet, ou faire face aux  besoins  ponctuels, comme lors du départ  à la retraite par exemple.<br>\n" +
                        "                            Si ses projets changent en cours de route, il garde  la possibilité  d&rsquo;accéder  à la part d&rsquo;épargne déterminée par la valeur  de rachat de son contrat.<br><br>\n" +
                        "                            <strong>Schématisation</strong>\n" +
                        "                        </p>\n" +
                        "                        <br>\n" +
                        "                        <p> <img src=\"../img/produits/schematisations.jpg\" /></p>\n" +
                        "                        <br>\n" +
                        "                        <p><strong>Caractéristiques</strong></p>\n" +
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
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>Constitution    et service d&rsquo;une rente par des versements périodiques, en vue de l&rsquo;éducation    des enfants</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>CONTRACTANT - SOUSCRIPTEUR  </strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p>Personnes    physiques, (titulaires d&rsquo;un compte courant ou d&rsquo;épargne)</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>ASSURES              </strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>Personnes    physiques </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>LIMITES D&rsquo;AGE A  LA SOUSCRIPTION </strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>Minimum&nbsp;: 18    ans&nbsp;; maximum&nbsp;: 60 ans</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>BENEFICIAIRES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p>Toute    personne physique désignée par le Souscripteur</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>DUREE DE PAIEMENT DES COTISATIONS </strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p>Minimum 5 ans&nbsp;: maximum 18 ans</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>NATURE DES GARANTIES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>Vie –    Décès – Invalidité Absolue et Définitive</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>LES GARANTIES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p><strong><u>Garantie en cas de vie</u></strong><strong>:</strong><br>\n" +
                        "                                    En cas de vie de l&rsquo;Assuré    au terme du contrat, paiement pendant 6 ans de la rente annuelle certaine  souscrite<br>\n" +
                        "                                    <strong><u>Garantie en cas de décès</u></strong><strong>:</strong><br>\n" +
                        "                                    En cas de Décès de    l&rsquo;Assuré avant le terme de la période de paiement des primes, paiement, à    chaque date anniversaire du contrat, de la rente annuelle certaine souscrite,    sur une durée partant du décès à la fin de la période prévue pour le paiement    des rentes en cas de vie. Application d&rsquo;un prorata au premier terme de rente.<br>\n" +
                        "                                    <strong><u>Garantie en cas d&rsquo;Invalidité Absolue et Définitive</u></strong><strong>:</strong><br>\n" +
                        "                                    En cas d&rsquo;Invalidité    Absolue et Définitive de l&rsquo;Assuré avant le terme de la période de paiement    des primes, paiement, à chaque date anniversaire du contrat, de la rente    annuelle certaine souscrite jusqu&rsquo;à la fin de la période prévue pour le    paiement des rentes en cas de vie. </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>MONTANT MINIMUM DES RENTES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p>Rente annuelle minimum    admissible&nbsp;: 100.000 F CFA </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>PERIODICITE DE PAIEMENT DES PRIMES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p>Elle    est choisie par le contractant&nbsp;; elle peut être mensuelle,    trimestrielle, semestrielle  ou    annuelle.</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>MONTANT DES PRIMES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p><strong><u>&nbsp;</u></strong></p>\n" +
                        "                                    <p>Les primes sont    déterminées en fonction de la rente annuelle souscrite, de la durée de    cotisations et de l&rsquo;âge de l&rsquo;assuré à la souscription. Voir l&rsquo;extrait    tarifaire.</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" rowspan=\"3\" valign=\"top\"><p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p>\n" +
                        "                                    <p><strong>DISPONIBILITE DU CAPITAL</strong></p>\n" +
                        "                                    <p><strong>&nbsp;</strong></p></td>\n" +
                        "                                <td width=\"113\" valign=\"top\"><p><strong>Rachat total</strong></p></td>\n" +
                        "                                <td width=\"397\" valign=\"top\"><p>Le droit au rachat    est ouvert après le paiement de deux (02) primes annuelles ou de 15% des    primes prévues au contrat.<br>\n" +
                        "                                    La valeur de    rachat est alors égale à la provision mathématique du contrat diminuée d&rsquo;une    pénalité de 5% applicable sur une durée maximale de 10 ans.</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"113\" valign=\"top\"><p><strong>Rachat partiel</strong></p></td>\n" +
                        "                                <td width=\"397\" valign=\"top\"><p>Ce contrat n&rsquo;autorise pas de rachats    partiels. </p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"113\" valign=\"top\"><p><strong>Avance</strong></p></td>\n" +
                        "                                <td width=\"397\" valign=\"top\"><p>Elle est possible après le paiement    effectif de deux (02) primes annuelles.<br>\n" +
                        "                                    Montants maximum&nbsp;: 95% de la valeur    de rachat, <br>\n" +
                        "                                    Montant minimum&nbsp;: 150&nbsp;000 F cfa</p></td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td width=\"142\" valign=\"top\"><p><strong>MODES DE PAIEMENT DES PRIMES</strong></p></td>\n" +
                        "                                <td width=\"510\" colspan=\"2\" valign=\"top\"><p>&nbsp;</p>\n" +
                        "                                    <p> Prélèvement    bancaire </p></td>\n" +
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
            javaMailSenderHandler.send("info.vie.ci@sahamassurance.com","demande de detail produit "+produit,"demande de detail formulé par "+nom+" dont le numero de telephone est :"+tel+"et l'adresse email est:"+email+"",false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }*//*
        data.put("succes",true);
        data.put("message", "merci pour votre interessement ,vous serez contacté");
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
            restResponse.setMessage("merci pour votre interessement ,vous serez contacté");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        }else {
            restResponse.setError(true);
            restResponse.setMessage("Aucun utilisateur trouvé");
        }
            return new ResponseBody<User>(user, restResponse);
    }
}
