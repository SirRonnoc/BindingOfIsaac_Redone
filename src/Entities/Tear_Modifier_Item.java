package Entities;

public class Tear_Modifier_Item extends Item{
    protected Class tearClass;

    public Tear_Modifier_Item(int xP, int yP, int h, int w, String[] effectTypes, int[] effectData, String t, Class c) {
        super(xP,yP,h,w,effectTypes,effectData,t);
        this.tearClass = c;
    }
    public Class getTearClass() {
        return this.tearClass;
    }
}
