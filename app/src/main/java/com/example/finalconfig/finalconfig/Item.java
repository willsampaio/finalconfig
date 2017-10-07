package com.example.finalconfig.finalconfig;

/**
 * Created by willer_sampaio on 25/09/2017.
 */

public class Item {

    private boolean conf_sinc;
    private boolean conf_wifi;
    private boolean conf_dados;
    private boolean conf_bt;
    private boolean conf_gps;
    private boolean conf_som;
    private boolean dia_dom;
    private boolean dia_seg;
    private boolean dia_ter;
    private boolean dia_qua;
    private boolean dia_qui;
    private boolean dia_sex;
    private boolean dia_sab;
    private String hora_inicio = "00:00", hora_fim = "00:00";
    int id;

    public Item() {
    }

    public Item(int id) {
        this.id = id;
    }


    public boolean isConf_sinc() {
        return conf_sinc;
    }

    public void setConf_sinc(boolean conf_sinc) {
        this.conf_sinc = conf_sinc;
    }

    public boolean isConf_wifi() {
        return conf_wifi;
    }

    public void setConf_wifi(boolean conf_wifi) {
        this.conf_wifi = conf_wifi;
    }

    public boolean isConf_dados() {
        return conf_dados;
    }

    public void setConf_dados(boolean conf_dados) {
        this.conf_dados = conf_dados;
    }

    public boolean isConf_bt() {
        return conf_bt;
    }

    public void setConf_bt(boolean conf_bt) {
        this.conf_bt = conf_bt;
    }

    public boolean isConf_gps() {
        return conf_gps;
    }

    public void setConf_gps(boolean conf_gps) {
        this.conf_gps = conf_gps;
    }

    public boolean isConf_som() {
        return conf_som;
    }

    public void setConf_som(boolean conf_som) {
        this.conf_som = conf_som;
    }

    public boolean isDia_dom() {
        return dia_dom;
    }

    public void setDia_dom(boolean dia_dom) {
        this.dia_dom = dia_dom;
    }

    public boolean isDia_seg() {
        return dia_seg;
    }

    public void setDia_seg(boolean dia_seg) {
        this.dia_seg = dia_seg;
    }

    public boolean isDia_ter() {
        return dia_ter;
    }

    public void setDia_ter(boolean dia_ter) {
        this.dia_ter = dia_ter;
    }

    public boolean isDia_qua() {
        return dia_qua;
    }

    public void setDia_qua(boolean dia_qua) {
        this.dia_qua = dia_qua;
    }

    public boolean isDia_qui() {
        return dia_qui;
    }

    public void setDia_qui(boolean dia_qui) {
        this.dia_qui = dia_qui;
    }

    public boolean isDia_sex() {
        return dia_sex;
    }

    public void setDia_sex(boolean dia_sex) {
        this.dia_sex = dia_sex;
    }

    public boolean isDia_sab() {
        return dia_sab;
    }

    public void setDia_sab(boolean dia_sab) {
        this.dia_sab = dia_sab;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(String hora_fim) {
        this.hora_fim = hora_fim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return id == item.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "conf_sinc=" + conf_sinc +
                ", conf_wifi=" + conf_wifi +
                ", conf_bt=" + conf_bt +
                ", conf_gps=" + conf_gps +
                ", conf_som=" + conf_som +
                ", dia_dom=" + dia_dom +
                ", dia_seg=" + dia_seg +
                ", dia_ter=" + dia_ter +
                ", dia_qua=" + dia_qua +
                ", dia_qui=" + dia_qui +
                ", dia_sex=" + dia_sex +
                ", dia_sab=" + dia_sab +
                ", hora_inicio='" + hora_inicio + '\'' +
                ", hora_fim='" + hora_fim + '\'' +
                ", id=" + id +
                '}';
    }
}
