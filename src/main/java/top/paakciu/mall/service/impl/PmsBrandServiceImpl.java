package top.paakciu.mall.service.impl;


import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.paakciu.mall.mbg.mapper.PmsBrandMapper;
import top.paakciu.mall.mbg.model.PmsBrand;
import top.paakciu.mall.mbg.model.PmsBrandExample;
import top.paakciu.mall.service.PmsBrandService;

import java.util.List;

/**
 * PmsBrandService实现类
 * Created by macro on 2019/4/19.
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
//        PageHelper.startPage(pageNum, pageSize);
//        //之后进行查询操作将自动进行分页
//        List<PmsBrand> brandList = brandMapper.selectByExample(new PmsBrandExample());
//        //通过构造PageInfo对象获取分页信息，如当前页码，总页数，总条数
//        PageInfo<PmsBrand> pageInfo = new PageInfo<PmsBrand>(list);
        List<PmsBrand> brandList=brandMapper.selectByExample(new PmsBrandExample());
        return brandList;
    }
    @Override
    public List<PmsBrand> listBrand(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        //之后进行查询操作将自动进行分页
        List<PmsBrand> brandList = brandMapper.selectByExample(new PmsBrandExample());
        //通过构造PageInfo对象获取分页信息，如当前页码，总页数，总条数
        //PageInfo<PmsBrand> pageInfo = new PageInfo<PmsBrand>(list);
        return brandList;
    }

    @Override
    public int createBrand(PmsBrand brand) {
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }


    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}
