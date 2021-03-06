/**
 * 
 */
package com.leimingtech.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.leimingtech.core.common.ParamsUtils;
import com.leimingtech.core.entity.base.Consult;
import com.leimingtech.core.freemarker.BaseFreeMarkerTag;
import com.leimingtech.service.module.trade.service.ConsultService;
import com.leimingtech.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

/**
	cgl
 */
@Component
public class ConsultInfoTag extends BaseFreeMarkerTag {

	@Resource
    private ConsultService consultService; 
	
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		//需要返回数据的类型 TagsDataType.java
		int pageNo = ParamsUtils.getInt(params.get("pageNo"));
		int goodsId = ParamsUtils.getInt(params.get("goodsId"));
		Pager pager = new Pager();
		if(pageNo!=0){
			pager.setPageNo(pageNo);
		}
        Consult consult = new Consult();
        consult.setGoodsId(goodsId);
        pager.setCondition(consult);// 实体加载在pager中
        
        pager.setResult(consultService.findList(pager));
        
        return pager;

        
	}

}
