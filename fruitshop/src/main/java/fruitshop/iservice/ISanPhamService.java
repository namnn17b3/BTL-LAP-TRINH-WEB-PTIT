package fruitshop.iservice;

import java.util.List;

import fruitshop.model.SanPham;

public interface ISanPhamService {
	public List<SanPham> getSanPhamOrderBySoLuongBan(int limit);
	public List<SanPham> getSanPhamOrderBySoSao(int limit);
	public SanPham getSanPhamById(int id);
}
