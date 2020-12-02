package top.paakciu.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import top.paakciu.mall.common.api.CommonResult;
import top.paakciu.mall.mbg.model.PmsBrand;
import top.paakciu.mall.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 品牌管理Controller
 * @author paakciu
 * 2020年12月2日 15:27:16
 *
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService demoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);


    /**显示全部
     *
     * @return
     */
    @ApiOperation("获取所有品牌列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getAllBrandList() {
        return CommonResult.success(demoService.listAllBrand());
    }

    /**分页查询
     * //@RequestMapping(value = "/listBrand", method = RequestMethod.GET)
     * //使用RESTful风格
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询")
    @GetMapping("/listBrand/{pageNum}/{pageSize}")
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList(@PathVariable
                                                         @ApiParam("页码") Integer pageNum,
                                                     @PathVariable
                                                     @ApiParam("每页大小")Integer pageSize) {
        List<PmsBrand> list=demoService.listBrand(pageNum,pageSize);
        System.out.println("访问brand.listBrand接口成功");
        return CommonResult.success(list);
    }

    /**增加
     *
     *
     * @param pmsBrand
     * @return
     */
    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = demoService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    /**更改
     *
     * @param id
     * @param pmsBrandDto
     * @param result
     * @return
     */
    @ApiOperation("修改指定id的品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
        CommonResult commonResult;
        int count = demoService.updateBrand(id, pmsBrandDto);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("updateBrand success:{}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}", pmsBrandDto);
        }
        return commonResult;
    }

    /**删除
     *
     * @param id
     * @return
     */
    @ApiOperation("删除指定ID的品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = demoService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    /**查找
     *
     * @param id
     * @return
     */
    @ApiOperation("根据指定id查询品牌")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(demoService.getBrand(id));
    }
}
