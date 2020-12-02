package top.paakciu.mall.service;




import top.paakciu.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * PmsBrandService
 * Created by macro on 2019/4/19.
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();
    List<PmsBrand> listBrand(Integer pageNum,Integer pageSize);

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    PmsBrand getBrand(Long id);
}
