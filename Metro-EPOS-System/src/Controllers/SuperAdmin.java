package Controllers;

public class SuperAdmin {
    private static  SuperAdmin superAdmin=null;
    private SuperAdmin() {
    }
    public static SuperAdmin getInstance(){
        if(superAdmin==null){
            superAdmin=new SuperAdmin();
        }
        return superAdmin;
    }
}
