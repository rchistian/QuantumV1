package com.modulo.main;


import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.CloseHandler;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;
import java.util.Vector;

@SuppressWarnings("serial")
public class TabMenu extends VerticalLayout implements TabSheet.SelectedTabChangeListener, TabSheet.CloseHandler {
    public TabSheet t;
    public Vector vectorTab = new Vector();
    
    public TabMenu() {
        t = new TabSheet();
        t.addListener(this);
        t.setCloseHandler(this);
        this.setMargin(false);
        
        addComponent(t);
    }

    public void selectedTabChange(SelectedTabChangeEvent event) {
        TabSheet tabsheet = event.getTabSheet();
        Tab tab = tabsheet.getTab(tabsheet.getSelectedTab());
        if (tab != null) {
            //getWindow().showNotification("Abriendo: " + tab.getCaption());
        }
    }
    
    public boolean isActiva(String OP){
        //t.getSelectedTab().
        return false;
    }

    @Override
    public void onTabClose(TabSheet tabsheet, Component tabContent) {
        /*getWindow().showNotification(
                "Cerrando: " + tabsheet.getTab(tabContent).getCaption());*/
        EliminarTab(tabsheet.getTab(tabContent).getCaption());
        tabsheet.removeComponent(tabContent);
    }
    
    public boolean VerificarTab(String Modulo){
        for(int f=0;f<vectorTab.size();f++){
            Tab TabAux= (Tab) vectorTab.get(f);
            if(TabAux.getCaption().equals(Modulo)){
                return true;
            }
        }
        return false;
    }
    
    public void EliminarTab(String Modulo){
        for(int f=0;f<vectorTab.size();f++){
            Tab TabAux= (Tab) vectorTab.get(f);
            if(TabAux.getCaption().equals(Modulo)){
                vectorTab.remove(f);
            }
        }
    }
    
    
}
