package my.lss2;

public class CharacterInfo {
    private String name;
    private String itemLV;
    private String job;
    private Boolean ck1;
    private Boolean ck2;

    public CharacterInfo(String name, String itemLV, String job, Boolean ck1, Boolean ck2){
        this.name = name;
        this.itemLV = itemLV;
        this.job = job;
        this.ck1 = ck1;
        this.ck2 = ck2;
    }

    public String getName() { return name; }

    public String getItemLV() { return itemLV; }

    public String getJob() { return job; }

    public Boolean getCk1() { return ck1 ; }

    public Boolean getCk2() { return ck2 ; }

    public void setName(String name) { this.name = name; }

    public void setItemLV(String itemLV) { this.itemLV = itemLV; }

    public void setJob(String job) { this.job = job; }

    public void setCk1(Boolean ck1) { this.ck1 = ck1; }

    public void setCk2(Boolean ck2) { this.ck2 = ck2; }
}
