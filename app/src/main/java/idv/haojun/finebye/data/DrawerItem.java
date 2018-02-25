package idv.haojun.finebye.data;

public class DrawerItem {
    public static final int MAP = 0;
    public static final int WARNING_SETTING = 1;
    public static final int FINE_BOT = 2;
    public static final int SEARCH = 3;
    public static final int THEME = 4;

    private int id;
    private int iconId;
    private int titleId;

    public DrawerItem(){

    }

    public DrawerItem(int id, int iconId, int titleId) {
        this.id = id;
        this.iconId = iconId;
        this.titleId = titleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }
}
