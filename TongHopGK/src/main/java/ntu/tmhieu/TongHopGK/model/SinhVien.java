package ntu.tmhieu.TongHopGK.model;

public class SinhVien {
    private String hoTen;
    private String ngaySinh;
    private String mssv;

    public SinhVien() {}

    public SinhVien(String hoTen, String ngaySinh, String mssv) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.mssv = mssv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
}
