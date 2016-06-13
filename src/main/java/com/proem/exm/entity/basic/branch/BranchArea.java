package com.proem.exm.entity.basic.branch;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

/**
 * @author DeFei
 * 分店区域
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_branch_area")
public class BranchArea extends Root{
	
	private static final long serialVersionUID = 7115174968885815406L;
	private String area_code;//分店区域编号
	private String area_name;//分店区域名称
}
