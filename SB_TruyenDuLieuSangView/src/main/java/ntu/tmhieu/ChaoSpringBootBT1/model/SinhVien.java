package ntu.tmhieu.ChaoSpringBootBT1.model;

public class SinhVien {
	private String mssv;
    private String hoTen;
    private int namSinh;
    private String gioiTinh;

    public SinhVien(String mssv, String hoTen, int namSinh, String gioiTinh) {
        this.mssv = mssv;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
    }

    public String getMssv() { return mssv; }
    public void setMssv(String mssv) { this.mssv = mssv; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public int getNamSinh() { return namSinh; }
    public void setNamSinh(int namSinh) { this.namSinh = namSinh; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
}
