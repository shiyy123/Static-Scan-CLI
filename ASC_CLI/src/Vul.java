public class Vul {
    private int id;
    private String name;
    private String zh_name;
    private String solution;
    private String zh_solution;
    private String desc;
    private String zh_desc;
    Vul(int id, String name, String zh_name, String solution, String zh_solution, String desc, String zh_desc){
        this.id = id;
        this.name = name;
        this.zh_name = zh_name;
        this.solution = solution;
        this.zh_solution = zh_solution;
        this.desc = desc;
        this.zh_desc = zh_desc;
    }
    Vul(){
        this.id = -1;
        this.name = "";
        this.zh_name = "";
        this.solution = "";
        this.zh_solution = "";
        this.desc = "";
        this.zh_desc = "";
    }

    public String toString(){
        return "id: " + this.id + "; " + "name: " + this.name + "; " + "solution: " + this.solution + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getZh_name() {
        return zh_name;
    }

    public void setZh_name(String zh_name) {
        this.zh_name = zh_name;
    }

    public String getZh_solution() {
        return zh_solution;
    }

    public void setZh_solution(String zh_solution) {
        this.zh_solution = zh_solution;
    }

    public String getZh_desc() {
        return zh_desc;
    }

    public void setZh_desc(String zh_desc) {
        this.zh_desc = zh_desc;
    }
}
