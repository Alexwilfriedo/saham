package com.digital.model.response;

public class SintreTempDeces {

    private String contrat;
   //Déclarant
    private String fullNameDecl;
   private String phone;
   private String adresse;
   private String mail;

   //Identification de l'Assuré
   private String fullNameAss;
   private String dateNaissance;
   private String lieuNaissance;
   private String profession;
   private String employeur;
   private String lieuResid;
   private boolean yesAssure;
   private boolean noAssure;
   private String de_pr;
   private String phoneMedecin;
   private String mailMedecin;
   private String centre;

   //Description du sinistre
   private String dateDeces;
    private String lieuDeces;
    private String causeDeces;

   private boolean ifDeces;
   private boolean invalide;
   private boolean original;
   private boolean extraitNaissance;
   private boolean identite;
   private boolean acteDeces;
   private boolean certificat;
   private boolean acteNaisBenef;
   private boolean acteNotoriete;
   private boolean certifApel;
   private boolean acteAdmin;
   private boolean acteMariage;
   private boolean pieceJusti;
   private boolean autrePiece;
   private boolean autre;

    public SintreTempDeces() {
    }

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public String getFullNameDecl() {
        return fullNameDecl;
    }

    public void setFullNameDecl(String fullNameDecl) {
        this.fullNameDecl = fullNameDecl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFullNameAss() {
        return fullNameAss;
    }

    public void setFullNameAss(String fullNameAss) {
        this.fullNameAss = fullNameAss;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmployeur() {
        return employeur;
    }

    public void setEmployeur(String employeur) {
        this.employeur = employeur;
    }

    public String getLieuResid() {
        return lieuResid;
    }

    public void setLieuResid(String lieuResid) {
        this.lieuResid = lieuResid;
    }

    public boolean isYesAssure() {
        return yesAssure;
    }

    public void setYesAssure(boolean yesAssure) {
        this.yesAssure = yesAssure;
    }

    public boolean isNoAssure() {
        return noAssure;
    }

    public void setNoAssure(boolean noAssure) {
        this.noAssure = noAssure;
    }

    public String getDe_pr() {
        return de_pr;
    }

    public void setDe_pr(String de_pr) {
        this.de_pr = de_pr;
    }

    public String getPhoneMedecin() {
        return phoneMedecin;
    }

    public void setPhoneMedecin(String phoneMedecin) {
        this.phoneMedecin = phoneMedecin;
    }

    public String getMailMedecin() {
        return mailMedecin;
    }

    public void setMailMedecin(String mailMedecin) {
        this.mailMedecin = mailMedecin;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public boolean isIfDeces() {
        return ifDeces;
    }

    public void setIfDeces(boolean ifDeces) {
        this.ifDeces = ifDeces;
    }

    public boolean isInvalide() {
        return invalide;
    }

    public void setInvalide(boolean invalide) {
        this.invalide = invalide;
    }

    public String getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(String dateDeces) {
        this.dateDeces = dateDeces;
    }

    public String getLieuDeces() {
        return lieuDeces;
    }

    public void setLieuDeces(String lieuDeces) {
        this.lieuDeces = lieuDeces;
    }

    public String getCauseDeces() {
        return causeDeces;
    }

    public void setCauseDeces(String causeDeces) {
        this.causeDeces = causeDeces;
    }

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

    public boolean isExtraitNaissance() {
        return extraitNaissance;
    }

    public void setExtraitNaissance(boolean extraitNaissance) {
        this.extraitNaissance = extraitNaissance;
    }

    public boolean isIdentite() {
        return identite;
    }

    public void setIdentite(boolean identite) {
        this.identite = identite;
    }

    public boolean isActeDeces() {
        return acteDeces;
    }

    public void setActeDeces(boolean acteDeces) {
        this.acteDeces = acteDeces;
    }

    public boolean isCertificat() {
        return certificat;
    }

    public void setCertificat(boolean certificat) {
        this.certificat = certificat;
    }

    public boolean isActeNaisBenef() {
        return acteNaisBenef;
    }

    public void setActeNaisBenef(boolean acteNaisBenef) {
        this.acteNaisBenef = acteNaisBenef;
    }

    public boolean isActeNotoriete() {
        return acteNotoriete;
    }

    public void setActeNotoriete(boolean acteNotoriete) {
        this.acteNotoriete = acteNotoriete;
    }

    public boolean isCertifApel() {
        return certifApel;
    }

    public void setCertifApel(boolean certifApel) {
        this.certifApel = certifApel;
    }

    public boolean isActeAdmin() {
        return acteAdmin;
    }

    public void setActeAdmin(boolean acteAdmin) {
        this.acteAdmin = acteAdmin;
    }

    public boolean isActeMariage() {
        return acteMariage;
    }

    public void setActeMariage(boolean acteMariage) {
        this.acteMariage = acteMariage;
    }

    public boolean isPieceJusti() {
        return pieceJusti;
    }

    public void setPieceJusti(boolean pieceJusti) {
        this.pieceJusti = pieceJusti;
    }

    public boolean isAutrePiece() {
        return autrePiece;
    }

    public void setAutrePiece(boolean autrePiece) {
        this.autrePiece = autrePiece;
    }

    public boolean isAutre() {
        return autre;
    }

    public void setAutre(boolean autre) {
        this.autre = autre;
    }
}
