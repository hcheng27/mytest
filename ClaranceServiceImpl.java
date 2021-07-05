/**
 * ClaranceServiceImpl.java
 * Created at 2019-03-22
 * Created by Huang.Songqing
 * Copyright (C) 2019 SEEBURGER, All rights reserved.
 */
package com.seeburger.webedi.solvay.ecustoms.clearance.decl.service.impl;


import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.ConsumerDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.ContainerDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.CustomsOfficeDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.DetailDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.EnterpriseQualificationDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.FeeDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.HeadDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.HsItemDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.HsItemLimitDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.HsItemSupplementDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.InspectionQuarantineDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.ItemAmountDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.ItemAttributeDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.ItemAuthDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.ItemContainerDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.ItemDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.OcrDetailDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.OcrHsItemDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.PackageDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.PartnerDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.TdBusdocRefDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.dao.TransportationDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdConsumer;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdContainer;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdDetail;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdDetailOcr;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdEnterpriseQualification;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdFee;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdHead;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdHsItem;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdHsItemLimit;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdHsItemOcr;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdHsItemSupplement;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdInspectionQuarantine;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdItem;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdItemAttribute;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdPartner;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TdTransportation;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.pojo.TrItemContainer;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.service.IClaranceService;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.util.MeiHuaOCRPort;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.ContainerVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.DraftVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.HeadQueryVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.ItemCompareVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.ItemPreviewVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.PreviewVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.WmsDataVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.CiqDict;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.CiqVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.HeadPreviewVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.HeadVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.HsDict;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.HsItemPreviewVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.HsItemVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.HsVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.ImpStatusVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.ImpVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.ItemVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.OverviewDictList;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.OverviewHeadVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.OverviewParty;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.imp.OverviewVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.ocr.DataVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.ocr.OcrItemVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.decl.vo.ocr.OcrRespVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.gts.dao.GtsHeadDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.gts.pojo.TiGtsDocument;
import com.seeburger.webedi.solvay.ecustoms.clearance.gts.service.IGtsService;
import com.seeburger.webedi.solvay.ecustoms.clearance.gts.utils.DocumentUtils;
import com.seeburger.webedi.solvay.ecustoms.clearance.kpi.dao.TbKpiDataDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.kpi.dao.TbKpiResultDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.kpi.pojo.TbKpiData;
import com.seeburger.webedi.solvay.ecustoms.clearance.kpi.pojo.TbKpiResult;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.dao.ToBusdocRefDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.dao.ToDetailDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.dao.ToDocumentDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.dao.ToHeadDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.dao.ToItemDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.dao.ToItemRelationshipDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.dao.ToItemSplitMergeRecordDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.dao.ToPartnerDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.dao.ToTransportationDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.pojo.ToDetail;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.pojo.ToDocument;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.pojo.ToHead;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.pojo.ToItem;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.pojo.ToItemRelationship;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.pojo.ToItemSplitMergeRecord;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.pojo.ToLicense;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.pojo.ToPartner;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.pojo.ToTransportation;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.service.IListService;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.utils.ListUtil;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.vo.BaseQty;
import com.seeburger.webedi.solvay.ecustoms.clearance.list.vo.ListItemVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.dao.DeclarationTaskDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.dao.DeclarationTaskStatusDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.dao.TdOutboundOrderDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.dao.TdTaxableGtsItemEstimateDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.dao.TransferDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.dao.TransferUserDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.dao.UserDeclarationTaskDao;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.pojo.TdDeclarationTask;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.pojo.TdDeclarationTaskStatus;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.service.IDeclTaskEmailService;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.utils.DeclUtils;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.vo.DeclTaskUserEmailVo;
import com.seeburger.webedi.solvay.ecustoms.clearance.task.vo.DeclaranceTaskVo;
import com.seeburger.webedi.solvay.ecustoms.collect.service.ICollectCustStatusService;
import com.seeburger.webedi.solvay.ecustoms.common.OpResult;
import com.seeburger.webedi.solvay.ecustoms.common.word.util.WordUtils;
import com.seeburger.webedi.solvay.ecustoms.config.mq.EmailStoreMq;
import com.seeburger.webedi.solvay.ecustoms.config.utils.EntityUtils;
import com.seeburger.webedi.solvay.ecustoms.excel.util.ExportExcelUtil;
import com.seeburger.webedi.solvay.ecustoms.masterdata.bu.util.BuUtils;
import com.seeburger.webedi.solvay.ecustoms.masterdata.doc.dao.TmDocumentTemplateDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.doc.pojo.TmDocumentTemplate;
import com.seeburger.webedi.solvay.ecustoms.masterdata.headtemplate.dao.HeadTemplateDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.headtemplate.pojo.TdHeadTemplate;
import com.seeburger.webedi.solvay.ecustoms.masterdata.headtemplate.vo.HeadTemplateQuery;
import com.seeburger.webedi.solvay.ecustoms.masterdata.headtemplate.vo.HeadTemplateVo;
import com.seeburger.webedi.solvay.ecustoms.masterdata.part.dao.PartAttributeDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.part.dao.PartDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.part.dao.TmPartPackageMaintainDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.part.pojo.TmPart;
import com.seeburger.webedi.solvay.ecustoms.masterdata.part.pojo.TmPartPackageMaintain;
import com.seeburger.webedi.solvay.ecustoms.masterdata.part.utils.DeptPartUtil;
import com.seeburger.webedi.solvay.ecustoms.masterdata.part.vo.ItemAttributeVo;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.dao.CiqAttributeDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.dao.InspectQuarantineDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.dao.PortDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.dao.TpAdministrativeDivisionDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.dao.TpCountryReginDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.dao.TpCustomsDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.dao.TpDomesticAreaDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.dao.TpDomesticPortDao;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.pojo.TpAdministrativeDivision;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.pojo.TpCiqAttribute;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.pojo.TpCountryRegin;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.pojo.TpCustoms;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.pojo.TpDomesticArea;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.pojo.TpDomesticPort;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.pojo.TpInsepctQuarantine;
import com.seeburger.webedi.solvay.ecustoms.masterdata.port.pojo.TpPort;
import com.seeburger.webedi.solvay.ecustoms.report.dao.BondedInvDetailDao;
import com.seeburger.webedi.solvay.ecustoms.report.dao.BondedInventoryDao;
import com.seeburger.webedi.solvay.ecustoms.report.pojo.TrBondedInvDetail;
import com.seeburger.webedi.solvay.ecustoms.report.pojo.TrBondedInventory;
import com.seeburger.webedi.solvay.ecustoms.report.service.ITdTaxableGtsItemService;
import com.seeburger.webedi.solvay.ecustoms.report.vo.BondedInvDetailVo;
import com.seeburger.webedi.solvay.ecustoms.report.vo.BondedInvQuery;
import com.seeburger.webedi.solvay.ecustoms.report.vo.TdTaxableGtsItemExcelVo;
import com.seeburger.webedi.solvay.ecustoms.system.dict.dao.DictDao;
import com.seeburger.webedi.solvay.ecustoms.system.dict.pojo.TmDataDict;
import com.seeburger.webedi.solvay.ecustoms.system.dict.service.IDictService;
import com.seeburger.webedi.solvay.ecustoms.system.dict.utils.DictUtils;
import com.seeburger.webedi.solvay.ecustoms.system.emailrecord.service.IEmailLinkRecordService;
import com.seeburger.webedi.solvay.ecustoms.system.emailrecord.util.EmailLinkUtils;
import com.seeburger.webedi.solvay.ecustoms.system.file.dao.FileRecordDao;
import com.seeburger.webedi.solvay.ecustoms.system.file.pojo.TlFileRecord;
import com.seeburger.webedi.solvay.ecustoms.system.file.utils.FileRecordUtils;
import com.seeburger.webedi.solvay.ecustoms.system.log.dao.InterfaceLogDao;
import com.seeburger.webedi.solvay.ecustoms.system.log.pojo.TlInterfaceLog;
import com.seeburger.webedi.solvay.ecustoms.system.user.dao.UserDao;
import com.seeburger.webedi.solvay.ecustoms.system.user.pojo.TsUserInfo;
import com.seeburger.webedi.solvay.ecustoms.system.user.util.UserUtils;
import com.seeburger.webedi.solvay.ecustoms.system.user.vo.user.UserEmailVo;
import com.seeburger.webedi.solvay.framework.util.Constants;
import com.seeburger.webedi.solvay.framework.util.DateUtils;
import com.seeburger.webedi.solvay.framework.util.FileUtil;
import com.seeburger.webedi.solvay.framework.util.Log;
import com.seeburger.webedi.solvay.framework.util.StringUtil;
import com.seeburger.webedi.solvay.framework.util.Utils;
import com.seeburger.webedi.solvay.framework.util.business.EmailUtil;
import com.seeburger.webedi.solvay.framework.vo.LoginUser;
import com.seeburger.webedi.solvay.framework.vo.SelectVo;
import com.seeburger.webedi.solvay.framework.vo.query.Paras;
import com.seeburger.webedi.solvay.framework.vo.query.QueryVo;
import com.seeburger.webedi.solvay.framework.vo.query.SortOrder;
import com.seeburger.webedi.solvay.notification.email.base.pojo.TnEmailInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>ClassName: ClaranceServiceImpl</p>
 * <p>Description: Clarance ServiceImpl</p>
 * <p>Author: Huang.Songqing</p>
 * <p>Date: 2019-03-22</p>
 */
@Service("claranceService")
public class ClaranceServiceImpl implements IClaranceService {

    /**
     * <p>Field headDao: 报关单证DAO</p>
     */
    @Resource
    private HeadDao headDao;
    /**
     * <p>Field toHeadDao: 主表DAO</p>
     */
    @Resource
    private ToHeadDao toHeadDao;
    /**
     * <p>Field itemDao: 商品DAO</p>
     */
    @Resource
    private ItemDao itemDao;
    /**
     * <p>Field itemAmountDao: 商品税费DAO</p>
     */
    @Resource
    private ItemAmountDao itemAmountDao;
    /**
     * <p>Field itemAttributeDao: 商品申报要素DAO</p>
     */
    @Resource
    private ItemAttributeDao itemAttributeDao;
    /**
     * <p>Field gtsItemAuthDao: 商品机构DAO</p>
     */
    @Resource
    private ItemAuthDao itemAuthDao;
    /**
     * <p>Field partnerDao: 伙伴DAO</p>
     */
    @Resource
    private PartnerDao partnerDao;
    /**
     * <p>Field transportationDao: 运输DAO</p>
     */
    @Resource
    private TransportationDao transportationDao;
    /**
     * <p>Field outboundOrderoDao: 出库单</p>
     */
    @Resource
    private TdOutboundOrderDao outboundOrderoDao;
    /**
     * <p>Field customsOfficeDao: 海关DAO</p>
     */
    @Resource
    private CustomsOfficeDao customsOfficeDao;
    /**
     * <p>Field packageDao: 包装DAO</p>
     */
    @Resource
    private PackageDao packageDao;
    /**
     * <p>Field containerDao: 集装箱DAO</p>
     */
    @Resource
    private ContainerDao containerDao;
    /**
     * <p>Field detailDao: 明细DAO</p>
     */
    @Resource
    private DetailDao detailDao;
    /**
     * <p>Field dictDao: 字典DAO</p>
     */
    @Resource
    private DictDao dictDao;
    /**
     * <p>Field feeDao: 费用DAO</p>
     */
    @Resource
    private FeeDao feeDao;
    /**
     * <p>Field itemContainerDao: 商品集装箱关系DAO</p>
     */
    @Resource
    private ItemContainerDao itemContainerDao;
    /**
     * <p>Field inspectionQuarantineDao: 检验检疫DAO</p>
     */
    @Resource
    private InspectionQuarantineDao inspectionQuarantineDao;
    /**
     * <p>Field consumerDao: 使用人DAO</p>
     */
    @Resource
    private ConsumerDao consumerDao;
    /**
     * <p>Field enterpriseQualificationDao: 企业资质DAO</p>
     */
    @Resource
    private EnterpriseQualificationDao enterpriseQualificationDao;
    /**
     * <p>Field ciqAttributeDao: 参数_检验检疫申报要素DAO</p>
     */
    @Resource
    private CiqAttributeDao ciqAttributeDao;
    /**
     * <p>Field hsItemDao: HS商品DAO</p>
     */
    @Resource
    private HsItemDao hsItemDao;
    /**
     * <p>Field hsItemSupplementDao: 报关单证_HS商品补充信息表DAO</p>
     */
    @Resource
    private HsItemSupplementDao hsItemSupplementDao;
    /**
     * <p>Field hsItemLimitDao: 报关单证_HS商品产品资质表DAO</p>
     */
    @Resource
    private HsItemLimitDao hsItemLimitDao;
    /**
     * <p>Field tpCustomsDao: 参数-关区代码DAO</p>
     */
    @Resource
    private TpCustomsDao tpCustomsDao;
    /**
     * <p>Field tpCountryReginDao: 国别（地区）代码DAO</p>
     */
    @Resource
    private TpCountryReginDao tpCountryReginDao;
    /**
     * <p>Field portDao: 港口代码DAO</p>
     */
    @Resource
    private PortDao portDao;
    /**
     * <p>Field inspectQuarantineDao: 参数-检验检疫名称DAO</p>
     */
    @Resource
    private InspectQuarantineDao inspectQuarantineDao;
    /**
     * <p>Field tpAdministrativeDivisionDao: 中华人民共和国行政区划代码DAO</p>
     */
    @Resource
    private TpAdministrativeDivisionDao tpAdministrativeDivisionDao;
    /**
     * <p>Field gtsService: gts Service</p>
     */
    @Resource
    private IGtsService gtsService;
    /**
     * <p>Field delclaranceTaskDao: 通关任务DAO</p>
     */
    @Resource
    private DeclarationTaskDao declarationTaskDao;
    /**
     * <p>Field delclaranceTaskStatusDao: 通关任务状态DAO</p>
     */
    @Resource
    private DeclarationTaskStatusDao declarationTaskStatusDao;
    /**
     * <p>Field userDao: user Dao</p>
     */
    @Resource
    private UserDao userDao;
    /**
     * <p>Field transferUserDao: 转单用户dao</p>
     */
    @Resource
    private TransferUserDao transferUserDao;
    /**
     * <p>Field userDelclaranceTaskDao: 通关任务与用户关系表DAO</p>
     */
    @Resource
    private UserDeclarationTaskDao userDeclarationTaskDao;
    /**
     * <p>Field tpDomesticPortDao: 国内口岸代码DAO</p>
     */
    @Resource
    private TpDomesticPortDao tpDomesticPortDao;
    /**
     * <p>Field tpDomesticAreaDao: 国内地区代码DAO</p>
     */
    @Resource
    private TpDomesticAreaDao tpDomesticAreaDao;
    /**
     * <p>Field tmDocumentTemplateDao: 文档模板DAO</p>
     */
    @Resource
    private TmDocumentTemplateDao tmDocumentTemplateDao;
    /**
     * <p>Field ToDocumentDao: 报关清单_随附单据记录表</p>
     */
    @Resource
    private ToDocumentDao toDocumentDao;
    /**
     * <p>Field partDao: 零件DAO</p>
     */
    @Resource
    private PartDao partDao;

    /**
     * <p>Field emailLinkRecordService: 邮件链接记录</p>
     */
    @Resource
    private IEmailLinkRecordService emailLinkRecordService;
    /**
     * <p>Field listService: listService</p>
     */
    @Resource
    private IListService listService;
    /**
     * <p>Field IDeclTaskEmailService: declTaskEmailService</p>
     */
    @Resource
    private IDeclTaskEmailService declTaskEmailService;
    /**
     * <p>Field dictService: 字典Service</p>
     */
    @Resource
    private IDictService dictService;
    /**
     * <p>Field fileRecordDao: fileRecord Dao</p>
     */
    @Resource
    private FileRecordDao fileRecordDao;
    /**
     * <p>Field partAttributeDao: 物料申报要素DAO</p>
     */
    @Resource
    private PartAttributeDao partAttributeDao;
    /**
     * Field transferDao: transferDao</p>
     */
    @Resource
    private TransferDao transferDao;
    /**
     * <p>Field headTemplateDao: 报关单证模板表DAO</p>
     */
    @Resource
    private HeadTemplateDao headTemplateDao;
    /**
     * <p>Field gtsHeadDao: GTS主表DAO</p>
     */
    @Resource
    private GtsHeadDao gtsHeadDao;
    /**
     * <p>Field collectCustStatusService: collectCustStatusService</p>
     */
    @Resource
    private ICollectCustStatusService collectCustStatusService;
    /**
     * <p>Field tdBusdocRefDao: tdBusdocRefDao</p>
     */
    @Resource
    private TdBusdocRefDao tdBusdocRefDao;

    /**
     * <p>Field toItemDao: toItemDao</p>
     */
    @Resource
    private ToItemDao toItemDao;
    /**
     * <p>Field toItemRelationshipDao: toItemRelationshipDao</p>
     */
    @Resource
    private ToItemRelationshipDao toItemRelationshipDao;
    /**
     * <p>Field toItemSplitMergeRecordDao: 商品拆分记录DAO</p>
     */
    @Resource
    private ToItemSplitMergeRecordDao toItemSplitMergeRecordDao;
    /**
     * <p>Field tdTaxableGtsItemService: 分拨费用分摊Service</p>
     */
    @Resource
    private ITdTaxableGtsItemService tdTaxableGtsItemService;

    /**
     * <p>Field tmPartPackageMaintainDao: tmPartPackageMaintainDao</p>
     */
    @Resource
    private TmPartPackageMaintainDao tmPartPackageMaintainDao;

    /**
     * <p>Field interfaceLogDao: 接口日志DAO</p>
     */
    @Resource
    private InterfaceLogDao interfaceLogDao;
    /**
     * <p>Field ocrDetailDao: OCR表头DAO</p>
     */
    @Resource
    private OcrDetailDao ocrDetailDao;
    /**
     * <p>Field ocrHsItemDao: OCR表体DAO</p>
     */
    @Resource
    private OcrHsItemDao ocrHsItemDao;
    /**
     * <p>Field tdTaxableGtsItemEstimateDao: 分拨付税预估Dao </p>
     */
    @Resource
    private TdTaxableGtsItemEstimateDao tdTaxableGtsItemEstimateDao;

    /**
     * <p>Field tbKpiDataDao: tbKpiDataDao</p>
     */
    @Resource
    private TbKpiDataDao tbKpiDataDao;
    /**
     * <p>Field bondedInvDetailDao: bondedInvDetailDao</p>
     */
    @Resource
    private BondedInvDetailDao bondedInvDetailDao;
    /**
     * <p>Field toPartnerDao: 供应商表DAO</p>
     */
    @Resource
    private ToPartnerDao toPartnerDao;
    /**
     * <p>Field bondedInvDetailDao: bondedInvDetailDao</p>
     */
    @Resource
    private BondedInventoryDao bondedInventoryDao;
    /**
     * <p>Field toDetailDao: toDetailDao</p>
     */
    @Resource
    private ToDetailDao toDetailDao;
    /**
     * <p>Field toTransportationDao: 运输表DAO</p>
     */
    @Resource
    private ToTransportationDao toTransportationDao;

    /**
     * <p>Field toBusdocRefDao: toBusdocRefDao</p>
     */
    @Resource
    private ToBusdocRefDao toBusdocRefDao;

    /**
     * <p>Field tbKpiResultDao: tbKpiResultDao</p>
     */
    @Resource
    private TbKpiResultDao tbKpiResultDao;

    /**
     * <p>Field tkpi: KPI贸易方式</p>
     */
    private String[] tkpi = new String[]{"s1", "s2", "s4", "s5", "s6", "s7", "s8"};
    /**
     * <p>Field imp: 进区贸易方式</p>
     */
    private String[] imp = new String[]{"s1", "s2", "s4", "s5"};
    /**
     * <p>Field exp: 出区贸易方式</p>
     */
    private String[] exp = new String[]{"s6", "s7", "s8"};

    /**
     * <p>Field df: 数字格式化</p>
     */
    private DecimalFormat df = new DecimalFormat("#.##");


    @Override
    public List<HeadVo> searchImportClearanceList(HeadQueryVo qv) {
        List<HeadVo> headVos = this.headDao.searchImportClearanceList(qv, UserUtils.getLoginUser());
        return headVos;
    }

    @Override
    public OverviewVo getOverviewInfo(QueryVo qv) throws IllegalAccessException, InvocationTargetException {
        TdHead head = this.headDao.get(qv.getId());
        if (null != head) {
            OverviewHeadVo headVo = new OverviewHeadVo();
            BeanUtils.copyProperties(headVo, head);
            OverviewVo ov = new OverviewVo();
            ov.setHead(headVo);
            ov.setImpExpMark(head.getIeType());
            ov.setEntryId(head.getEntryId());
            ov.setCusCiqNo(head.getCusCiqNo());
            ov.setTaskStatus(this.declarationTaskDao.findStatusById(head.getTaskId()));
            TdDetail detail = this.detailDao.findByHeadId(qv.getId());
            if (null != detail) {
                BeanUtils.copyProperties(ov, detail);
                if (StringUtil.isNotBlank(detail.getOtherPackaging())) {
                    ov.setOtherPackagings(Utils.splitList(detail.getOtherPackaging(), Constants.COMMA));
                }
                if (StringUtil.isNotBlank(detail.getBusinessMatter())) {
                    ov.setBusinessMatters(Utils.splitList(detail.getBusinessMatter(), Constants.COMMA));
                }
                ov.setDetailId(detail.getId());

            }

            TdTransportation trans = this.transportationDao.findByHeadId(qv.getId());
            if (null != trans) {
                BeanUtils.copyProperties(ov, trans);
            }
            if (StringUtil.isNotBlank(detail.getEntryStatus())) {
                ov.setDeclareStatus(detail.getEntryStatus());
            }
            ov.setDeclareDate(Utils.parseDate(detail.getClChecklistDeclarationDate()));
            List<String> typelist = new ArrayList<>();
            typelist.add(Constants.Partner.IMPORTER);
            typelist.add(Constants.Partner.DECLARANT);
            typelist.add(Constants.Partner.EXPORTER);
            typelist.add(Constants.Partner.REPRESENTATIVE_DECLARANT);
            typelist.add(Constants.Partner.COP);
            typelist.add(Constants.Partner.CL_PROCESSING);
            typelist.add(Constants.Partner.CL_REPRESENTATIVE_DECLARANT);
            typelist.add(Constants.Partner.CL_PRODUCTIONANDSALES);

            List<OverviewParty> parties = this.partnerDao.searchOverParty(qv.getId(), typelist);
            if (Utils.isNotEmpty(parties)) {
                for (OverviewParty party : parties) {
                    switch (party.getType()) {
                        case Constants.Partner.IMPORTER:
                            ov.setImportParty(party);
                            break;
                        case Constants.Partner.DECLARANT:
                            ov.setDeclaranParty(party);
                            break;
                        case Constants.Partner.EXPORTER:
                            ov.setExportParty(party);
                            break;
                        case Constants.Partner.REPRESENTATIVE_DECLARANT:
                            ov.setRepDeclaranParty(party);
                            break;
                        case Constants.Partner.COP:
                            ov.setCopParty(party);
                            break;
                        case Constants.Partner.CL_PROCESSING:
                            ov.setClProcessingParty(party);
                            break;
                        case Constants.Partner.CL_REPRESENTATIVE_DECLARANT:
                            ov.setClRepDeclaranParty(party);
                            break;
                        case Constants.Partner.CL_PRODUCTIONANDSALES:
                            ov.setClProductionAndSaleParty(party);
                            break;
                        default:
                            break;
                    }
                }
            }

            TdFee fee = this.feeDao.findByHeadId(qv.getId());
            if (null != fee) {
                BeanUtils.copyProperties(ov, fee);
            }
            return ov;
        }
        return null;
    }

    @Override
    public OverviewDictList getOverviewDict(QueryVo qv) {

        OverviewDictList dict = new OverviewDictList();

        List<Long> idlist = new ArrayList<>();
        idlist.add(Constants.DictType.BUSINESS_TYPE);
        idlist.add(Constants.DictType.DECL_TRANS_MARK);
        idlist.add(Constants.DictType.DECLARATION_MARK);
        idlist.add(Constants.DictType.DOCUMENT_TYPE);
        idlist.add(Constants.DictType.RECORD_DOC_TYPE);
        idlist.add(Constants.DictType.CHKTST_FLAG);
        idlist.add(Constants.DictType.INSEPCTION_DIVERSION);
        idlist.add(Constants.DictType.TAX_ADMIN_MARK);
        idlist.add(Constants.DictType.NO_OTHER_PACKAGING);
        idlist.add(Constants.DictType.OTHER_PACKAGING);
        idlist.add(Constants.DictType.ENTRY_TYPE);
        idlist.add(Constants.DictType.SPEC_REL_CONFIRM);
        // 判断业务事项显示
        if (Constants.BusinessType.IMPORT.equals(qv.getType())) {
            idlist.add(Constants.DictType.BUSINESS_MATTERS);
        } else if (Constants.BusinessType.EXPORT.equals(qv.getType())) {
            idlist.add(Constants.DictType.BUSINESS_MATTERS2);
        } else if (Constants.BusinessType.RECORD_IMPORT.equals(qv.getType())) {
            idlist.add(Constants.DictType.BUSINESS_MATTERS_RECORD_IMP);
        } else if (Constants.BusinessType.RECORD_EXPORT.equals(qv.getType())) {
            idlist.add(Constants.DictType.BUSINESS_MATTERS_RECORD_EXP);
        }
        idlist.add(Constants.DictType.P_CUT_MODE);
        idlist.add(Constants.DictType.P_PACKAGE_TYPE);
        idlist.add(Constants.DictType.P_SUPERVISION_MODE);
        idlist.add(Constants.DictType.P_TRANS_MODE);
        idlist.add(Constants.DictType.P_TRANSPORTATION_MODE);
        idlist.add(Constants.DictType.CURRENCY_COMPARE);
        idlist.add(Constants.DictType.FEE_MARK);
        idlist.add(Constants.DictType.FEE_MARK2);

        List<SelectVo> list = this.dictDao.selectListWithTypeIdList(idlist, qv.getLanguage());
        if (Utils.isNotEmpty(list)) {
            for (SelectVo vo : list) {
                dict.addDict(vo);
            }
        }

        return dict;
    }

    @Override
    @Transactional
    public String saveOverview(OverviewVo ov, LoginUser user) throws IllegalAccessException, InvocationTargetException {

        Date now = new Date();
        // 更新明细表
        if ("RE".equals(ov.getHead().getBusinessType()) || "RI".equals(ov.getHead().getBusinessType())) { // 进出境备案清单
            ov.setDeclarationTransMark("0"); // 默认报关/转关关系标志填写0-一般报关单
            ov.setDeclarationMark("1"); // 报关标志默认填写1-普通报关
        }
        TdDetail detail = this.detailDao.get(ov.getDetailId());
        BeanUtils.copyProperties(detail, ov);
        detail.setOtherPackaging(Utils.join(ov.getOtherPackagings(), Constants.COMMA));
        detail.setBusinessMatter(Utils.join(ov.getBusinessMatters(), Constants.COMMA));
        detail.setHeadId(ov.getHead().getId());
        detail.setUpdateTime(now);
        detail.setUpdateBy(user.getUserNo());
        this.detailDao.save(detail);

        // 监管方式是否有变更
        boolean tradeModeChange = false;
        // 成交方式是否有变更
        boolean tansModeChange = false;
        // 更新运输表
        TdTransportation trans = this.transportationDao.findByHeadId(ov.getHead().getId());
        tradeModeChange = !Utils.equals(ov.getTradeMode(), trans.getTradeMode());
        tansModeChange = !Utils.equals(ov.getTransMode(), trans.getTransMode());
        BeanUtils.copyProperties(trans, ov);
        trans.setUpdateTime(now);
        trans.setUpdateBy(user.getUserNo());
        this.transportationDao.save(trans);

        if (tradeModeChange) {
            if (null != trans.getTradeMode()) {
                String taxFreeMethod = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.CHECKLIST_SUPERVISION_TAX_FREE_RELATION, trans.getTradeMode());
                if (null != taxFreeMethod) {
                    this.hsItemDao.updateItemTaxFreeMethod(ov.getHead().getId(), taxFreeMethod);
                }
            }
        }

        // 更新伙伴表
        this.savePartner(ov.getDeclaranParty(), now, user.getUserNo(), ov.getHead().getId(), Constants.Partner.DECLARANT);
        this.savePartner(ov.getImportParty(), now, user.getUserNo(), ov.getHead().getId(), Constants.Partner.IMPORTER);
        this.savePartner(ov.getExportParty(), now, user.getUserNo(), ov.getHead().getId(), Constants.Partner.EXPORTER);
        this.savePartner(ov.getRepDeclaranParty(), now, user.getUserNo(), ov.getHead().getId(), Constants.Partner.REPRESENTATIVE_DECLARANT);
        this.savePartner(ov.getCopParty(), now, user.getUserNo(), ov.getHead().getId(), Constants.Partner.COP);
        this.savePartner(ov.getClProcessingParty(), now, user.getUserNo(), ov.getHead().getId(), Constants.Partner.CL_PROCESSING);
        this.savePartner(ov.getClRepDeclaranParty(), now, user.getUserNo(), ov.getHead().getId(), Constants.Partner.CL_REPRESENTATIVE_DECLARANT);
        this.savePartner(ov.getClProductionAndSaleParty(), now, user.getUserNo(), ov.getHead().getId(), Constants.Partner.CL_PRODUCTIONANDSALES);

        // 更新费用
        TdFee fee = this.feeDao.findByHeadId(ov.getHead().getId());
        //运费类型是否变化
        boolean freightMarkChange = false;
        freightMarkChange = !Utils.equals(ov.getFreightMark(), fee.getFreightMark());

        BeanUtils.copyProperties(fee, ov);
        fee.setUpdateTime(now);
        fee.setUpdateBy(user.getUserNo());
        this.feeDao.save(fee);

        // 更新通关任务的状态:保存报关单时，判断通过任务的当前状态，如果是未制单，则修改为：已制单未报关
        TdDeclarationTask task = this.declarationTaskDao.findByDeclId(ov.getHead().getId());
        if (task != null && task.getStatus().equals(Constants.Dtask.STATUS_NO_CREATED)) {
            task.setStatus(Constants.Dtask.STATUS_INIT);
            this.declarationTaskDao.save(task);
        }
        /*单证详情中成交方式改变或运费类型改变时重新计算清单物料CIF单价 start*/
        ToHead th = this.toHeadDao.get(task.getListId());
        // 当核注清单IQD标识为“D”时，表体字段计算逻辑
        if (null != th  && (Constants.BusinessType.CHECKLIST_EXPORT.equals(th.getBusinessType()) || Constants.BusinessType.CHECKLIST_IMPORT.equals(th.getBusinessType())) && Constants.InvMark.INV_MARK_ADD.equals(th.getIqdMark())) {
            if (tansModeChange) { /*单证成交方式改变时核注清单成交方式同步变化*/
                ToTransportation toTransportation = this.toTransportationDao.findByHeadId(th.getId());
                toTransportation.setTransMode(trans.getTransMode());
                this.toTransportationDao.save(toTransportation);
            }
            /*CIF单价，计算保留4位小数：
            当报关单成交方式为1 - CIF时，CIF单价 = 申报单价；
            当报关单成交方式不是1 - CIF时，CIF单价 = 申报单价 + (报关单运费总价 + 申报总价 * 0.3%）/ 报关单总净重 * 物料行法定数量 / 申报数量；
            当运费类型为总价时，计算CIF单价，当运费类型为单价或者费率时，CIF单价不计算*/
            if (null != task && (tansModeChange || freightMarkChange)) { /*成交方式或运费类型改变重新计算CIF单价*/
                List<ToItem> items = this.toItemDao.findByHeadId(th.getId());
                if (Utils.isNotEmpty(items)) {
                    if (!"1".equals(trans.getTransMode())) { /*成交方式不为1 - CIF*/
                        if ("3".equals(fee.getFreightMark())) {
                            /*运费类型为3-总价: CIF单价 = 申报单价 + (报关单运费总价 + 申报总价 * 0.3%）/ 报关单总净重 * 物料行法定数量 / 申报数量*/
                            //获取单证总净重
                            BigDecimal totalNetWeight = trans.getTotalNetweight();
                            for (ToItem item : items) {
                                //申报总价 * 0.3%
                                BigDecimal cifPrice = item.getCurrencyAmount().multiply(new BigDecimal("0.003"));
                                // +报关单运费总价
                                cifPrice = cifPrice.add(fee.getFreightValue());
                                // / 报关单总净重
                                cifPrice = cifPrice.divide(totalNetWeight, 8, BigDecimal.ROUND_HALF_UP);
                                // * 物料行法定数量
                                cifPrice = cifPrice.multiply(item.getUnitQty());
                                // /申报数量
                                cifPrice = cifPrice.divide(new BigDecimal(item.getQuantity()), 4, BigDecimal.ROUND_HALF_UP);
                                //+申报单价
                                cifPrice = cifPrice.add(item.getUnitPrice());
                                item.setCifPrice(cifPrice);
                            }
                        }
                    } else { /*成交方式为1 - CIF;CIF单价 = 申报单价；*/
                        for (ToItem item : items) {
                            item.setCifPrice(item.getUnitPrice());
                        }
                    }
                    this.toItemDao.saveAll(items);
                }
            }

        }
        /*单证详情中成交方式改变或运费类型改变时重新计算清单物料CIF单价 end*/

        return null;
    }

    @Override
    public CiqVo getCiq(QueryVo qv) throws IllegalAccessException, InvocationTargetException {
        CiqVo vo = new CiqVo();
        TdInspectionQuarantine iq = this.inspectionQuarantineDao.findByHeadId(qv.getId());
        if (null != iq) {
            BeanUtils.copyProperties(vo, iq);
            vo.setCiqId(iq.getId());
            if (StringUtil.isNotBlank(iq.getSpecDeclFlag())) {
                vo.setSpecDeclFlagList(Utils.splitList(iq.getSpecDeclFlag(), Constants.COMMA));
            }
        }
        List<TdEnterpriseQualification> eqlist = this.enterpriseQualificationDao.findByHeadId(qv.getId());
        if (Utils.isNotEmpty(eqlist)) {
            for (TdEnterpriseQualification eq : eqlist) {
                EntityUtils.removeDate(eq);
            }
            vo.setEqlist(eqlist);
        }
        List<TdConsumer> consumers = this.consumerDao.findByHeadId(qv.getId());
        if (Utils.isNotEmpty(consumers)) {
            for (TdConsumer consumer : consumers) {
                EntityUtils.removeDate(consumer);
            }
            vo.setConsumers(consumers);
        }
        vo.setHeadId(qv.getId());
        return vo;
    }

    @Override
    public CiqDict getCiqDict(QueryVo qv) {
        CiqDict dict = new CiqDict();

        List<Long> idlist = new ArrayList<>();
        idlist.add(Constants.DictType.P_ENTERPRISE_QUALIFICATION_CATEGORY);
        idlist.add(Constants.DictType.P_RELATED_REASON);
        idlist.add(Constants.DictType.SPEC_DECL_FLAG);
        idlist.add(Constants.DictType.ORIGINAL_BOX_TRANSPORT);

        List<SelectVo> list = this.dictDao.selectListWithTypeIdList(idlist, qv.getLanguage());
        if (Utils.isNotEmpty(list)) {
            for (SelectVo vo : list) {
                dict.addDict(vo);
            }
        }
        List<TpCiqAttribute> ciqAttributes = this.ciqAttributeDao.findAll();
        if (Utils.isNotEmpty(ciqAttributes)) {
            for (TpCiqAttribute ciqAttribute : ciqAttributes) {
                ciqAttribute.setCreateTime(null);
            }
            dict.setCiqAttributes(ciqAttributes);
        }

        return dict;
    }

    @Override
    public String saveCiq(CiqVo cv, LoginUser user) throws IllegalAccessException, InvocationTargetException {
        Date now = new Date();

        TdInspectionQuarantine iq = this.inspectionQuarantineDao.get(cv.getCiqId());
        BeanUtils.copyProperties(iq, cv);
        iq.setSpecDeclFlag(Utils.join(cv.getSpecDeclFlagList(), Constants.COMMA));
        iq.setUpdateBy(user.getUserNo());
        iq.setUpdateTime(now);
        this.inspectionQuarantineDao.save(iq);
        return null;
    }

    /**
     * <p>Description: 保存伙伴信息</p>
     *
     * @param party  伙伴
     * @param now    时间
     * @param userNo 用户号
     * @param id     ID
     * @param type   类型
     */
    private void savePartner(OverviewParty party, Date now, String userNo, Long id, String type) {
        if (null != party) {
            TdPartner dp = null;
            if (null != party.getId()) {
                dp = this.partnerDao.get(party.getId());
                if (null == dp) {
                    dp = new TdPartner();
                }
            } else {
                dp = this.partnerDao.findByHeadIdAndType(id, type);
                if (null == dp) {
                    dp = new TdPartner();
                }
            }
            if (null == dp.getCreateTime()) {
                dp.setType(type);
                dp.setHeadId(id);
                dp.setCreateTime(now);
                dp.setCreateBy(userNo);
                dp.setDeleteMark(Constants.NO);
            }
            dp.setSocialCreditCode(party.getCode());
            dp.setCustomsCode(party.getCustomsCode());
            dp.setCiqCode(party.getCiqCode());
            dp.setPartnerName(party.getName());
            dp.setUpdateBy(userNo);
            dp.setUpdateTime(now);
            this.partnerDao.save(dp);
        }
    }

    @Override
    @Transactional
    public String saveConsumer(TdConsumer save, LoginUser user) {
        save.init(user, new Date());
        this.consumerDao.save(save);
        return null;
    }

    @Override
    public String deleteConsumer(Long id, LoginUser user) {
        this.consumerDao.deleteById(id);
        return null;
    }

    @Override
    public String saveEq(TdEnterpriseQualification save, LoginUser user) {
        save.init(user, new Date());
        this.enterpriseQualificationDao.save(save);
        return null;
    }

    @Override
    public String deleteEq(Long id, LoginUser user) {
        this.enterpriseQualificationDao.deleteById(id);
        return null;
    }

    @Override
    public List<ItemVo> searchItemByHeadId(QueryVo qv) {
        return this.itemDao.searchByHeadId(qv);
    }

    @Override
    public List<HsItemVo> searchHsItemByHeadId(QueryVo qv) {
        return this.hsItemDao.searchByHeadId(qv);
    }

    @Override
    public HsVo getHsItem(QueryVo qv) throws IllegalAccessException, InvocationTargetException {
        HsVo vo = new HsVo();
        TdHsItem hsItem = this.hsItemDao.get(qv.getId());
        BeanUtils.copyProperties(vo, hsItem);
        vo.setMainId(hsItem.getId());
        vo.setTdItemId(hsItem.getItemId());
        TdHsItemSupplement supplement = this.hsItemSupplementDao.findByItemId(qv.getId());
        if (null != supplement) {
            BeanUtils.copyProperties(vo, supplement);
            if (StringUtil.isNotBlank(supplement.getCargoAttribute())) {
                vo.setCargoAttributes(Utils.splitList(supplement.getCargoAttribute(), Constants.COMMA));
            }
        }
        vo.setAttrs(this.itemAttributeDao.findByItemId(hsItem.getId()));
        vo.setGmodel(Utils.white(Utils.removeSplitter(this.itemAttributeDao.getAttributeByItemId(hsItem.getId()))));
        List<TdHsItemLimit> license = this.hsItemLimitDao.findByItemId(qv.getId());
        if (Utils.isNotEmpty(license)) {
            for (TdHsItemLimit pq : license) {
                EntityUtils.removeDate(pq);
            }
            vo.setLicense(license);
        }
        return vo;
    }

    @Override
    public HsDict getHsItemDict(QueryVo qv) {
        HsDict dict = new HsDict();

        List<Long> idlist = new ArrayList<>();
        idlist.add(Constants.DictType.P_TAX_REDUCTION_EXEMPTION);
        idlist.add(Constants.DictType.CURRENCY_COMPARE);
        idlist.add(Constants.DictType.P_CARGO_ATTRIBUTE);
        idlist.add(Constants.DictType.P_PURPOSE_TYPE);
        idlist.add(Constants.DictType.P_MEASUREMENT_UNIT);
        idlist.add(Constants.DictType.P_LICENSE_CATEGORY);
        idlist.add(Constants.DictType.DANGEROUS_PACKAGE_CATEGORY);
        idlist.add(Constants.DictType.NONHAZARDOUS_CHEMICAL);
        idlist.add(Constants.DictType.CHECKLIST_USAGE);
        idlist.add(Constants.DictType.UPDATE_MARK);
        idlist.add(Constants.DictType.P_USE_CODE);
        List<SelectVo> list = this.dictDao.selectListWithTypeIdList(idlist, qv.getLanguage());
        if (Utils.isNotEmpty(list)) {
            for (SelectVo vo : list) {
                dict.addDict(vo);
            }
        }
        return dict;
    }

    @Override
    @Transactional
    public String savePq(TdHsItemLimit pq, LoginUser user) {
        pq.init(user, new Date());
        this.hsItemLimitDao.save(pq);
        return null;
    }

    @Override
    @Transactional
    public String updatePq(TdHsItemLimit pq, LoginUser user) {
        TdHsItemLimit entity = this.hsItemLimitDao.get(pq.getId());
        entity.setVinNo(pq.getVinNo());
        entity.setBillLadDate(DateUtils.parseDate(pq.getBillLadDateView()));
        entity.setQualityQgp(pq.getQualityQgp());
        entity.setMotorNo(pq.getMotorNo());
        entity.setVinCode(pq.getVinCode());
        entity.setChassisNo(pq.getChassisNo());
        entity.setInvoiceNum(pq.getInvoiceNum());
        entity.setProdCnnm(pq.getProdCnnm());
        entity.setProdEnnm(pq.getProdEnnm());
        entity.setModelEn(pq.getModelEn());
        entity.setPricePerUnit(pq.getPricePerUnit());
        entity.setInvoiceNo(pq.getInvoiceNo());
        entity.setUpdateBy(user.getUserNo());
        entity.setUpdateTime(new Date());
        this.hsItemLimitDao.save(entity);
        return null;
    }

    @Override
    public String deletePq(Long id, LoginUser user) {

        // TODO 添加权限判断
        this.hsItemLimitDao.deleteById(id);
        return null;
    }

    @Override
    @Transactional
    public String saveHsItem(HsVo vo, LoginUser user) throws IllegalAccessException, InvocationTargetException {
        Date now = new Date();
        if (!DeclUtils.verifyAttributeLength(vo.getAttrs())) {
            return OpResult.OVERLENGTH.getCode();
        }
        TdHsItem item = this.hsItemDao.get(vo.getMainId());
        if (null == item) {
            item = new TdHsItem();
            item.init(user, now);
        }
        BeanUtils.copyProperties(item, vo);
        if (StringUtil.isBlank(vo.getExtItemNo())) {
            item.setExtItemNo(null);
        }
        if (item.getCiqId() != null) {
            Long ciqId = item.getCiqId();
            TpInsepctQuarantine ciq = this.inspectQuarantineDao.get(ciqId);
            if (ciq != null) {
                item.setCiqCode(ciq.getCiqCode());
                item.setCiqName(ciq.getCiqName());
            }
        }
        item.update(user, now);
        // 币制为人民币CNY时，价格是否替换标识
        if ("CNY".equals(item.getCurrencyCode())) {
            item.setRepMark(Constants.YES);
        } else {
            item.setRepMark(Constants.NO);
        }
        this.hsItemDao.save(item);

        /*更新明细包装种类及其他包装start*/
        List<TdHsItem> hsItems = this.hsItemDao.findByHeadIdOrderByItemNoAsc(item.getHeadId());
        TdHead head = this.headDao.get(item.getHeadId());

        Long ownerId = head.getOwnerId();
        Long deptId = head.getDeptId();
        TdDetail detail = this.detailDao.findByHeadId(item.getHeadId());
        String packageType2 = ""; // 包装种类，获取第一个有值的
        String noOtherPackaging = ""; // 无其他包装
        String otherPackaging = ""; // 其他包装，获取全部物料的其他包装
        // 用于判断包装种类是否一致，不一致，则取第一条物料为包装种类，其他物料的不同包装种类为其他包装
        List<String> others = new ArrayList<>();
        if (Utils.isNotEmpty(hsItems)) {
            for (TdHsItem tdHsItem : hsItems) {
                String producdNo = tdHsItem.getProductNo();
                // 查询物料主数据
                TmPart tmPart = this.partDao.findAllByCompanyAndDeptAndPartNum(producdNo, ownerId, deptId);
                tmPart = DeptPartUtil.switchPart(tmPart, producdNo, ownerId, deptId);
                if (tmPart != null) {
                    if ("".equals(packageType2)) {
                        packageType2 = StringUtil.trimToEmpty(tmPart.getPackageType2());
                    }
                    if ("".equals(noOtherPackaging)) {
                        noOtherPackaging = StringUtil.trimToEmpty(tmPart.getNoOtherPacking());
                    }
                    // 无其他包装且物料内包装种类不一致，则修改其他包装为有其他包装，将该条物料的包装种类放入其他包装字段内
                    if (("1".equals(noOtherPackaging) || "".equals(noOtherPackaging)) && others.size() < 1) {
                        if (StringUtil.isNotBlank(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) {
                            noOtherPackaging = "0";
                            otherPackaging = tmPart.getPackageType2();
                            others.add(tmPart.getPackageType2());
                        }
                    }
                    if ("0".equals(noOtherPackaging)) {
                        if ("".equals(otherPackaging)) { //其他包装为空
                            otherPackaging = StringUtil.trimToEmpty(tmPart.getOtherPacking());
                            if (others.size() > 0 && StringUtil.isNotBlank(tmPart.getPackageType2())) { //其他包装判断list不为空，且包装种类不为空
                                if (!others.contains(tmPart.getPackageType2()) && !otherPackaging.contains(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) {
                                    otherPackaging += "," + tmPart.getPackageType2();
                                    others.add(tmPart.getPackageType2());
                                }
                            } else if (StringUtil.isNotBlank(tmPart.getPackageType2()) && !otherPackaging.contains(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) { //包装种类不为空
                                otherPackaging += "," + tmPart.getPackageType2();
                                others.add(tmPart.getPackageType2());
                            }
                        } else { //其他包装不为空
                            if (!"".equals(tmPart.getOtherPacking()) && !otherPackaging.contains(StringUtil.trimToEmpty(tmPart.getOtherPacking()))) {
                                otherPackaging += "," + StringUtil.trimToEmpty(tmPart.getOtherPacking());
                            }
                            if (others.size() > 0 && StringUtil.isNotBlank(tmPart.getPackageType2())) {
                                if (!others.contains(tmPart.getPackageType2()) && !otherPackaging.contains(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) {
                                    otherPackaging += "," + tmPart.getPackageType2();
                                    others.add(tmPart.getPackageType2());
                                }
                            } else if (StringUtil.isNotBlank(tmPart.getPackageType2())) {
                                if (!otherPackaging.contains(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) {
                                    otherPackaging += "," + tmPart.getPackageType2();
                                    others.add(tmPart.getPackageType2());
                                }
                            }
                        }
                    }
                }

            }
            detail.setPackageType(packageType2);
            detail.setNoOtherPackaging(noOtherPackaging);
            for (int i = 0; ; ) {
                if (otherPackaging.contains(",,")) {
                    otherPackaging = otherPackaging.replaceAll(",,", ",");
                } else {
                    break;
                }
            }
            // 其他包装种类去重
            String[] arr = otherPackaging.split(",");
            //（将数据放入到新的List里面）
            List<String> list = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if (!list.contains(arr[i])) {
                    if (i == 0) {
                        otherPackaging = arr[i];
                    } else {
                        otherPackaging = otherPackaging + "," + arr[i];
                    }
                    list.add(arr[i]);
                }
            }
            detail.setOtherPackaging(otherPackaging);
            this.detailDao.save(detail);
        }
        /*end*/
// ------------单证保存失败 start-------------
//        if (null != item.getItemId()) {
//            TdItem tdItem = this.itemDao.get(item.getItemId());
//            if (null != tdItem) { // 非空判断
//                BeanUtils.copyProperties(tdItem, vo);
//                if (StringUtil.isBlank(vo.getExtItemNo())) {
//                    tdItem.setExtItemNo(null);
//                }
//                tdItem.setProductNo(vo.getHsCode());
//                tdItem.setProductName(vo.getHsName());
//                tdItem.update(user, now);
//                this.itemDao.save(tdItem);
//            }
//        } else {
//            List<TdItem> list = this.itemDao.findByHeadIdAndRelItemNo(item.getHeadId(), item.getItemNo());
//            if (Utils.isNotEmpty(list)) {
//                for (TdItem tdItem : list) {
//                    tdItem.setOriginCountry(vo.getOriginCountry());
//                    tdItem.setDestinationCountry(vo.getDestinationCountry());
//                    tdItem.setUnit(vo.getUnit());
//                    tdItem.update(user, now);
//                }
//                this.itemDao.saveAll(list);
//            }
//        }
// ------------单证保存失败 end-------------
        TdHsItemSupplement supp = this.hsItemSupplementDao.findByItemId(vo.getMainId());
        if (null == supp) {
            supp = new TdHsItemSupplement();
            supp.init(user, now);
            supp.setHeadId(item.getHeadId());
            supp.setItemId(item.getId());
        }
        BeanUtils.copyProperties(supp, vo);
        supp.setCargoAttribute(Utils.join(vo.getCargoAttributes(), Constants.COMMA));
        supp.update(user, now);
        this.hsItemSupplementDao.save(supp);

        if (vo.isAttrChange()) {
            this.updateAttribute(vo, user, now, item.getId(), item.getHeadId());
        }
        vo.setResId(item.getId());
        return null;
    }

    /**********************合并表体********************************/
    @Override
    public String mergeItem(List<Long> ids, LoginUser user) throws InvocationTargetException, IllegalAccessException {
        String result = null;
        if (Utils.isNotEmpty(ids) && 1 < ids.size()) {
            Date now = new Date();
            // 报关单
            List<TdHsItem> tdHsItems = this.hsItemDao.findByIdIn(ids);
            if (Utils.isNotEmpty(tdHsItems) && tdHsItems.size() == ids.size()) {
                this.hsItemDao.deleteByIdIn(ids);
                // 合并
                TdHsItem tdHsItem = this.convertTdHsItem(tdHsItems);
                tdHsItem.setItemNo(0);
                this.hsItemDao.save(tdHsItem);
                // hs商品补充信息

                // hs商品许可证

            }
        }
        return null;
    }

    private TdHsItem convertTdHsItem(List<TdHsItem> items) throws InvocationTargetException, IllegalAccessException {
        if (Utils.isNotEmpty(items)) {
            List<TdHsItem> merge = this.merge(items);
            return merge.get(0);
        }
        return null;
    }

    private List<TdHsItem> merge(List<TdHsItem> items) throws InvocationTargetException, IllegalAccessException {
        if (Utils.isNotEmpty(items)) {
            Map<String, TdHsItem> map = new LinkedHashMap<>();
            int i = 1;
            TdHsItem it = new TdHsItem();
            for (TdHsItem item : items) {
                it = new TdHsItem();
                BeanUtils.copyProperties(it, item);
                String key = this.createKey(it);
                if (map.containsKey(key)) {
                    map.put(key, this.merge(map.get(key), it));
                } else {
                    it.setItemNo(i++);
                    map.put(key, it);

                }
            }
            return new ArrayList<>(map.values());
        }
        return null;
    }

    /**
     * <p>Description: 创建唯一条件</p>
     *
     * @param item 物料
     * @return KEY
     */
    private String createKey(TdHsItem item) {
        List<String> list = new ArrayList<>();
        list.add(Utils.white(item.getProductNo()));
        list.add(Utils.white(item.getOriginCountry()));
        list.add(Utils.white(item.getHsCode()));
        list.add(Utils.white(item.getDeclareUnit()));
        list.add(Utils.white(item.getCurrencyCode()));
        return Utils.join(list, "-");
    }

    private TdHsItem merge(TdHsItem item, TdHsItem from) {
        item.setQuantity(Utils.add(item.getQuantity(), from.getQuantity()));
        item.setCurrencyAmount(Utils.add(item.getCurrencyAmount(), from.getCurrencyAmount()));
        item.setNetWeight(Utils.add(item.getNetWeight(), from.getNetWeight()));
        item.setGrossWeight(Utils.add(item.getGrossWeight(), from.getGrossWeight()));
        return item;
    }

    /********************end**************************/
    @Override
    @Transactional
    public String addHsItem(HsVo vo, LoginUser user) throws IllegalAccessException, InvocationTargetException {
        Date now = new Date();
        if (!DeclUtils.verifyAttributeLength(vo.getAttrs())) {
            return OpResult.OVERLENGTH.getCode();
        }
        TdHsItem item = new TdHsItem();
        BeanUtils.copyProperties(item, vo);
        if (item.getCiqId() != null) {
            Long ciqId = item.getCiqId();
            TpInsepctQuarantine ciq = this.inspectQuarantineDao.get(ciqId);
            if (ciq != null) {
                item.setCiqCode(ciq.getCiqCode());
                item.setCiqName(ciq.getCiqName());
            }
        }
        item.init(user, now);
        item.setHeadId(vo.getHeadId());
        item.setItemNo(this.hsItemDao.selectMaxItemNo(vo.getHeadId()) + 1);
        // 币制为人民币CNY时，价格是否替换标识
        if ("CNY".equals(item.getCurrencyCode())) {
            item.setRepMark(Constants.YES);
        } else {
            item.setRepMark(Constants.NO);
        }
        this.hsItemDao.save(item);

        /*更新明细包装种类及其他包装start*/
        List<TdHsItem> hsItems = this.hsItemDao.findByHeadIdOrderByItemNoAsc(item.getHeadId());
        TdHead head = this.headDao.get(item.getHeadId());
        Long ownerId = head.getOwnerId();
        Long deptId = head.getDeptId();
        TdDetail detail = this.detailDao.findByHeadId(item.getHeadId());
        String packageType2 = ""; // 包装种类，获取第一个有值的
        String noOtherPackaging = ""; // 无其他包装
        String otherPackaging = ""; // 其他包装，获取全部物料的其他包装
        // 用于判断包装种类是否一致，不一致，则取第一条物料为包装种类，其他物料的不同包装种类为其他包装
        List<String> others = new ArrayList<>();
        if (Utils.isNotEmpty(hsItems)) {
            for (TdHsItem tdHsItem : hsItems) {
                String producdNo = tdHsItem.getProductNo();
                // 查询物料主数据
                TmPart tmPart = this.partDao.findAllByCompanyAndDeptAndPartNum(producdNo, ownerId, deptId);
                tmPart = DeptPartUtil.switchPart(tmPart, producdNo, ownerId, deptId);
                if (tmPart != null) {
                    if ("".equals(packageType2)) {
                        packageType2 = StringUtil.trimToEmpty(tmPart.getPackageType2());
                    }
                    if ("".equals(noOtherPackaging)) {
                        noOtherPackaging = StringUtil.trimToEmpty(tmPart.getNoOtherPacking());
                    }
                    // 无其他包装且物料内包装种类不一致，则修改其他包装为有其他包装，将该条物料的包装种类放入其他包装字段内
                    if (("1".equals(noOtherPackaging) || "".equals(noOtherPackaging)) && others.size() < 1) {
                        if (StringUtil.isNotBlank(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) {
                            noOtherPackaging = "0";
                            otherPackaging = tmPart.getPackageType2();
                            others.add(tmPart.getPackageType2());
                        }
                    }
                    if ("0".equals(noOtherPackaging)) {
                        if ("".equals(otherPackaging)) { //其他包装为空
                            otherPackaging = StringUtil.trimToEmpty(tmPart.getOtherPacking());
                            if (others.size() > 0 && StringUtil.isNotBlank(tmPart.getPackageType2())) { //其他包装判断list不为空，且包装种类不为空
                                if (!others.contains(tmPart.getPackageType2()) && !otherPackaging.contains(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) {
                                    otherPackaging += "," + tmPart.getPackageType2();
                                    others.add(tmPart.getPackageType2());
                                }
                            } else if (StringUtil.isNotBlank(tmPart.getPackageType2()) && !otherPackaging.contains(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) { //包装种类不为空
                                otherPackaging += "," + tmPart.getPackageType2();
                                others.add(tmPart.getPackageType2());
                            }
                        } else { //其他包装不为空
                            if (!"".equals(tmPart.getOtherPacking()) && !otherPackaging.contains(StringUtil.trimToEmpty(tmPart.getOtherPacking()))) {
                                otherPackaging += "," + StringUtil.trimToEmpty(tmPart.getOtherPacking());
                            }
                            // otherPackaging += "," + StringUtil.trimToEmpty(tmPart.getOtherPacking());
                            if (others.size() > 0 && StringUtil.isNotBlank(tmPart.getPackageType2())) {
                                if (!others.contains(tmPart.getPackageType2()) && !otherPackaging.contains(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) {
                                    otherPackaging += "," + tmPart.getPackageType2();
                                    others.add(tmPart.getPackageType2());
                                }
                            } else if (StringUtil.isNotBlank(tmPart.getPackageType2())) {
                                if (!otherPackaging.contains(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) {
                                    otherPackaging += "," + tmPart.getPackageType2();
                                    others.add(tmPart.getPackageType2());
                                }
                            }
                        }
                    }
                }

            }

            detail.setPackageType(packageType2);
            detail.setNoOtherPackaging(noOtherPackaging);
            for (int i = 0; ; ) {
                if (otherPackaging.contains(",,")) {
                    otherPackaging = otherPackaging.replaceAll(",,", ",");
                } else {
                    break;
                }
            }
            // 其他包装种类去重
            String[] arr = otherPackaging.split(",");
            //（将数据放入到新的List里面）
            List<String> list = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if (!list.contains(arr[i])) {
                    if (i == 0) {
                        otherPackaging = arr[i];
                    } else {
                        otherPackaging = otherPackaging + "," + arr[i];
                    }
                    list.add(arr[i]);
                }
            }

            detail.setOtherPackaging(otherPackaging);
            this.detailDao.save(detail);
        }
        /*end*/

        TdItem tdItem = new TdItem();
        BeanUtils.copyProperties(tdItem, vo);
        tdItem.init(user, now);
        tdItem.setHeadId(vo.getHeadId());
        tdItem.setHsItemId(item.getId());
        tdItem.setItemNo(item.getItemNo());
        tdItem.setRelItemNo(item.getItemNo());
        tdItem.setProductNo(vo.getProductNo());
        tdItem.setProductName(vo.getHsName());
        tdItem.setItemNo(item.getItemNo());
        this.itemDao.save(tdItem);
        vo.setTdItemId(tdItem.getId());
        item.setItemId(tdItem.getId());
        this.hsItemDao.save(item);

        TdHsItemSupplement supp = new TdHsItemSupplement();
        supp.setHeadId(item.getHeadId());
        supp.setItemId(item.getId());
        BeanUtils.copyProperties(supp, vo);
        supp.init(user, now);
        supp.setCargoAttribute(Utils.join(vo.getCargoAttributes(), Constants.COMMA));
        this.hsItemSupplementDao.save(supp);

        this.addAttribute(vo, user, now, item.getId(), item.getHeadId());
        vo.setResId(item.getId());
        return null;
    }

    /**
     * <p>Description: 添加物料申报要素</p>
     *
     * @param vo     数据
     * @param user   用户
     * @param now    时间
     * @param itemId 物料ID
     * @param headId 主表ID
     */
    private void addAttribute(HsVo vo, LoginUser user, Date now, Long itemId, Long headId) {
        if (Utils.isNotEmpty(vo.getAttrs())) {
            // 如果ID不为空则更新.为空则删除重新保存
            List<TdItemAttribute> attributes = new ArrayList<>();
            for (ItemAttributeVo attr : vo.getAttrs()) {
                attributes.add(attr.convertTdItemAttribute(itemId, headId, user.getUserNo(), now));
            }
            this.itemAttributeDao.saveAll(attributes);
        }
    }

    /**
     * <p>Description: 更新物料申报要素</p>
     *
     * @param vo     数据
     * @param user   用户
     * @param now    时间
     * @param itemId 物料ID
     * @param headId 主表ID
     */
    private void updateAttribute(HsVo vo, LoginUser user, Date now, Long itemId, Long headId) {
        if (Utils.isNotEmpty(vo.getAttrs())) {
            // 如果ID不为空则更新.为空则删除重新保存
            if (null != vo.getAttrs().get(0).getId()) {
                for (ItemAttributeVo attr : vo.getAttrs()) {
                    if (null != attr.getId()) {
                        this.itemAttributeDao.updateValue(attr.getValue(), user.getUserNo(), now, attr.getId());
                    }
                }
            } else {
                this.itemAttributeDao.deleteByItemId(itemId);
                List<TdItemAttribute> attributes = new ArrayList<>();
                for (ItemAttributeVo attr : vo.getAttrs()) {
                    attributes.add(attr.convertTdItemAttribute(itemId, headId, user.getUserNo(), now));
                }
                this.itemAttributeDao.saveAll(attributes);
            }
        }
    }

    @Override
    @Transactional
    public String deleteHsItem(Long id, LoginUser user) throws IllegalAccessException, InvocationTargetException {
        TdHsItem item = this.hsItemDao.get(id);
        if (null != item) {
            this.hsItemSupplementDao.deleteByItemId(id);
            this.hsItemDao.deleteById(id);
            // this.itemDao.deleteById(item.getItemId());
            this.itemAttributeDao.deleteByItemId(id);
            List<TdHsItem> hsItems = this.hsItemDao.findByHeadIdOrderByItemNoAsc(item.getHeadId());
            // 更新商品序号，确保商品序号连续
            if (Utils.isNotEmpty(hsItems)) {
                List<Long> ids = new ArrayList<>();
                int itemNo = 1;
                for (TdHsItem tdHsItem : hsItems) {
                    this.hsItemDao.updateItemNo(itemNo, tdHsItem.getId());
                    itemNo++;
                }
            }
            /*更新明细包装种类及其他包装start*/
            TdHead head = this.headDao.get(item.getHeadId());
            Long ownerId = head.getOwnerId();
            Long deptId = head.getDeptId();
            TdDetail detail = this.detailDao.findByHeadId(item.getHeadId());
            String packageType2 = ""; // 包装种类，获取第一个有值的
            String noOtherPackaging = ""; // 无其他包装
            String otherPackaging = ""; // 其他包装，获取全部物料的其他包装
            // 用于判断包装种类是否一致，不一致，则取第一条物料为包装种类，其他物料的不同包装种类为其他包装
            List<String> others = new ArrayList<>();
            if (Utils.isNotEmpty(hsItems)) {
                for (TdHsItem tdHsItem : hsItems) {
                    String producdNo = tdHsItem.getProductNo();
                    // 查询物料主数据
                    TmPart tmPart = this.partDao.findAllByCompanyAndDeptAndPartNum(producdNo, ownerId, deptId);
                    tmPart = DeptPartUtil.switchPart(tmPart, producdNo, ownerId, deptId);
                    if (tmPart != null) {
                        if ("".equals(packageType2)) {
                            packageType2 = StringUtil.trimToEmpty(tmPart.getPackageType2());
                        }
                        if ("".equals(noOtherPackaging)) {
                            noOtherPackaging = StringUtil.trimToEmpty(tmPart.getNoOtherPacking());
                        }
                        // 无其他包装且物料内包装种类不一致，则修改其他包装为有其他包装，将该条物料的包装种类放入其他包装字段内
                        if (("1".equals(noOtherPackaging) || "".equals(noOtherPackaging)) && others.size() < 1) {
                            if (StringUtil.isNotBlank(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) {
                                noOtherPackaging = "0";
                                otherPackaging = tmPart.getPackageType2();
                                others.add(tmPart.getPackageType2());
                            }
                        }
                        if ("0".equals(noOtherPackaging)) {
                            if ("".equals(otherPackaging)) { //其他包装为空
                                otherPackaging = StringUtil.trimToEmpty(tmPart.getOtherPacking());
                                if (others.size() > 0 && StringUtil.isNotBlank(tmPart.getPackageType2())) { //其他包装判断list不为空，且包装种类不为空
                                    if (!others.contains(tmPart.getPackageType2()) && !otherPackaging.contains(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) {
                                        otherPackaging += "," + tmPart.getPackageType2();
                                        others.add(tmPart.getPackageType2());
                                    }
                                } else if (StringUtil.isNotBlank(tmPart.getPackageType2()) && !otherPackaging.contains(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) { //包装种类不为空
                                    otherPackaging += "," + tmPart.getPackageType2();
                                    others.add(tmPart.getPackageType2());
                                }
                            } else { //其他包装不为空
                                if (!"".equals(tmPart.getOtherPacking()) && !otherPackaging.contains(StringUtil.trimToEmpty(tmPart.getOtherPacking()))) {
                                    otherPackaging += "," + StringUtil.trimToEmpty(tmPart.getOtherPacking());
                                }
                                if (others.size() > 0 && StringUtil.isNotBlank(tmPart.getPackageType2())) {
                                    if (!others.contains(tmPart.getPackageType2()) && !otherPackaging.contains(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) {
                                        otherPackaging += "," + tmPart.getPackageType2();
                                        others.add(tmPart.getPackageType2());
                                    }
                                } else if (StringUtil.isNotBlank(tmPart.getPackageType2())) {
                                    if (!otherPackaging.contains(tmPart.getPackageType2()) && !packageType2.equals(tmPart.getPackageType2())) {
                                        otherPackaging += "," + tmPart.getPackageType2();
                                        others.add(tmPart.getPackageType2());
                                    }
                                }
                            }
                        }
                    }

                }
                detail.setPackageType(packageType2);
                detail.setNoOtherPackaging(noOtherPackaging);
                for (int i = 0; ; ) {
                    if (otherPackaging.contains(",,")) {
                        otherPackaging = otherPackaging.replaceAll(",,", ",");
                    } else {
                        break;
                    }
                }
                // 其他包装种类去重
                String[] arr = otherPackaging.split(",");
                //（将数据放入到新的List里面）
                List<String> list = new ArrayList<>();
                for (int i = 0; i < arr.length; i++) {
                    if (!list.contains(arr[i])) {
                        if (i == 0) {
                            otherPackaging = arr[i];
                        } else {
                            otherPackaging = otherPackaging + "," + arr[i];
                        }
                        list.add(arr[i]);
                    }
                }
                detail.setOtherPackaging(otherPackaging);
                this.detailDao.save(detail);
            }
            /*end*/

        }
        return null;
    }

    @Override
    public List<TdContainer> listContainer(QueryVo qv) {
        Paras pa = new Paras();
        if (null != qv.getId()) {
            pa.add("headId", qv.getId());
        }
        qv.orderBy("seqNo", SortOrder.ASC);
        List<TdContainer> list = this.containerDao.findListBy(pa, qv);
        for (TdContainer entity : list) {
            entity.setLclMarkDesc(DictUtils.getValue(Constants.DictType.YON, entity.getLclMark()));
            entity.setContainerSpecDesc(DictUtils.getValue(Constants.DictType.P_CONTAINER_SPEC, entity.getContainerSpec()));
            List<TrItemContainer> itemContainers = this.itemContainerDao.findByContainerId(entity.getId());
            List<Long> itemIds = new ArrayList<>();
            itemContainers.forEach(p -> itemIds.add(p.getItemId()));
            entity.setItemRelation(itemIds.toArray(new Long[itemIds.size()]));
        }
        return list;
    }

    @Override
    @Transactional
    public void deleteContainer(Long id) {
//        this.containerDao.deleteById(id);
        TdContainer con = this.containerDao.get(id);
        Long headId = con.getHeadId();
        this.containerDao.delete(con);
        // 更新序号
        List<TdContainer> list = this.containerDao.findByHeadId(headId);
        Integer seqNo = 1;
        if (Utils.isNotEmpty(list)) {
            for (TdContainer container : list) {
                container.setSeqNo(seqNo);
                seqNo = seqNo + 1;
            }
        }
        this.containerDao.saveAll(list);
        this.itemContainerDao.deleteByContainerId(con.getId());
        // 更新集装箱数
        this.updateContainerNum(headId);
    }

    @Override
    @Transactional
    public OpResult addContainer(TdContainer entity) {
        String goodsNo = null;
        if (entity.getItemRelation() != null) {
            for (Long itemId : entity.getItemRelation()) {
                TdHsItem item = this.hsItemDao.get(itemId);
                if (null == goodsNo) {
                    goodsNo = item.getItemNo().toString();
                } else {
                    goodsNo = goodsNo + "," + item.getItemNo().toString();
                }
            }
        }
        entity.setGoodsNo(goodsNo);
        entity.setCreateTime(new Date());
        entity.setDeleteMark(Constants.NO);
        this.containerDao.save(entity);
        saveItemContainer(entity.getId(), entity.getItemRelation(), entity.getCreateBy(), entity.getHeadId());

        // 更新集装箱数
        updateContainerNum(entity.getHeadId());
        return OpResult.OK;

    }

    /**
     * <p>Description: 更新集装箱数</p>
     *
     * @param headId 主表ID
     */
    private void updateContainerNum(Long headId) {
        TdDetail detail = this.detailDao.findByHeadId(headId);
        if (null != detail) {
//            List<TdContainer> containerList = this.containerDao.findByHeadId(headId);
            List<ContainerVo> conList = this.containerDao.searchContainerListByHeadId(headId);
            if (null != conList) {
                int containerNumber = this.sumContainerNum(conList);
                detail.setContainerNumber(containerNumber);
                this.detailDao.save(detail);
            }
        }
    }

    /**
     * <p>Description: 根据集装箱规格累计集装箱数</p>
     *
     * @param conList 集装箱信息
     * @return 箱数
     */
    private int sumContainerNum(List<ContainerVo> conList) {
        int ret = 0;
        if (null != conList) {
            for (ContainerVo vo : conList) {
                String desc = StringUtil.trimToEmpty(vo.getContainerSpecDesc());
                if (desc.contains("2*")) {
                    ret += 2;
                } else {
                    ret += 1;
                }

            }
        }
        return ret;
    }

    /**
     * <p>Description: 商品与集装箱关系表 保存</p>
     *
     * @param containerId 集装箱ID
     * @param itemIds     商品ID
     * @param userNo      当前用户号
     * @param headId      主表ID
     */
    private void saveItemContainer(Long containerId, Long[] itemIds, String userNo, Long headId) {
        this.itemContainerDao.deleteByContainerId(containerId);
        if (itemIds != null) {
            for (Long itemId : itemIds) {
                TrItemContainer itemContainer = new TrItemContainer();
                itemContainer.setContainerId(containerId);
                itemContainer.setItemId(itemId);
                itemContainer.setHeadId(headId);
                itemContainer.init(userNo, new Date());
                this.itemContainerDao.save(itemContainer);
            }
        }
    }

    @Override
    @Transactional
    public OpResult updateContainer(TdContainer entity) {
        TdContainer container = this.containerDao.get(entity.getId());
        String goodsNo = null;
        if (null != entity.getItemRelation()) {
            for (Long itemId : entity.getItemRelation()) {
                TdHsItem item = this.hsItemDao.get(itemId);
                if (null != item) {
                    if (null == goodsNo) {
                        goodsNo = item.getItemNo().toString();
                    } else {
                        goodsNo = goodsNo + "," + item.getItemNo().toString();
                    }
                }
            }
        }
        container.setSeqNo(entity.getSeqNo());
        container.setGoodsNo(goodsNo);
        container.setContainerNo(entity.getContainerNo());
        container.setContainerSpec(entity.getContainerSpec());
        container.setDeadWeight(entity.getDeadWeight());
        container.setLclMark(entity.getLclMark());
        container.setCargoWeight(entity.getCargoWeight());
        container.setUpdateBy(entity.getUpdateBy());
        container.setUpdateTime(new Date());
        this.containerDao.save(container);
        this.saveItemContainer(entity.getId(), entity.getItemRelation(), entity.getUpdateBy(), entity.getHeadId());

        // 更新集装箱数
        this.updateContainerNum(entity.getHeadId());
        return OpResult.OK;
    }

    @Override
    public List<TdHsItem> listItem(QueryVo qv) {
        Paras pa = new Paras();
        if (null != qv.getId()) {
            pa.add("headId", qv.getId());
        }
        qv.orderBy("createTime", SortOrder.DESC);
        return this.hsItemDao.findListBy(pa, qv);
    }

    @Override
    @Transactional
    public String uploadVerificatioDraft(MultipartFile file, Long listId, Long headId, Long taskId, String docNo, String docType, LoginUser user) throws IOException {
        Date now = new Date();
        String valid = DocumentUtils.verifyFile(file, docType);
        if (StringUtil.isBlank(valid)) {
            List<ToDocument> document = this.toDocumentDao.findByTaskId(taskId); // 根据任务id获取随附单据
            // 定义list保存随附单据的文件名称
            List<String> documentNames = new ArrayList<>();
            if (Utils.isNotEmpty(document)) {
                for (ToDocument name : document) {
                    documentNames.add(name.getDocFileName());
                }
            }
            String pdfType = null;
            // 遍历上传文件list,如果存在则为文件重命名
            String fileName = file.getOriginalFilename();
            if (documentNames.contains(fileName)) {
                String name = fileName.substring(0, fileName.lastIndexOf(".")); // 文件前缀
                String filetype = fileName.substring(fileName.lastIndexOf(".") + 1); // 文件后缀
                pdfType = filetype;
                String newFileName = name + "(" + 1 + ")." + filetype;
                if (!documentNames.contains(newFileName)) {
                    fileName = newFileName;
                } else if (documentNames.contains(newFileName)) {
                    fileName = this.listService.fileRename(1, newFileName, documentNames);
                }
            } else {
                fileName = file.getOriginalFilename();
                pdfType = fileName.substring(fileName.lastIndexOf(".") + 1); // 文件后缀
            }

//            String fileName = file.getOriginalFilename();
            String path = FileUtil.createPath(DictUtils.getValue(Constants.DictType.FOLDER, Constants.Folder.DOCUMENT_UPLOAD));
            DocumentUtils.uploadDarftDocument(file, fileName, path, listId, taskId, docNo, docType, "P", false);
            Long fileId = FileRecordUtils.save(fileName, path, Constants.FILE_RECORD_TYPE_UPLOAD, "upload.declaration.draft", user.getUserNo());

            TdDeclarationTask task = this.declarationTaskDao.get(taskId);
            task.setStatus(Constants.Dtask.STATUS_FIRST_TRIAL_PENDING);
            task.setUpdateTime(now);
            task.setUpdateBy(user.getUserNo());
            this.declarationTaskDao.save(task);

            TdHead head = this.headDao.get(task.getDeclId());
            head.setStatus(Constants.IMP_STATUS_GENERATE);
            head.setSideStatus(Constants.IMP_STATUS_UPLOAD_FIRST);
            head.setUpdateTime(now);
            head.setUpdateBy(user.getUserNo());
            this.headDao.save(head);

            TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
            status.setTaskId(task.getId());
            status.setStatus(Constants.Dtask.STATUS_FIRST_TRIAL_PENDING);
            status.setOperation(Constants.Dtask.OPER_UPLOAD_FIRST);
            status.setLinkId(fileId);
            status.setLinkContent(fileName);
            status.setIsRead(Constants.NO);
            DeclUtils.setTaskStatusCompany(status, user);
            status.init(user, now);
            this.declarationTaskStatusDao.save(status);
            // 上传文件为报关校对稿时
            if (Constants.DICT_CODE_DOC_TYPE_BGJYG.equals(docType) && "pdf".equals(pdfType)) {
                String url = DictUtils.getValue(Constants.DictType.INTERFACE, Constants.Interface.MEIHUA_OCR_URL);
                TlInterfaceLog log = new TlInterfaceLog();
                log.setCreateBy(user.getUserNo());
                log.setCreateTime(now);

                MeiHuaOCRPort.send(url, task.getTaskNo(), log, file, path);
                OcrRespVo respVo = MeiHuaOCRPort.parse(log.getResp());
                if (null != respVo) {
                    if ("1".equals(respVo.getStatus())) {
                        log.setStatus(Constants.Log.SUCCEED);
                    } else {
                        log.setStatus(Constants.Log.FAILED);
//                        this.sendEmailToMeihua(path, fileName, task.getTaskNo(), log.getResp());
                    }
                } else {
                    log.setStatus(Constants.Log.FAILED);
//                    this.sendEmailToMeihua(path, fileName, task.getTaskNo(), log.getResp());
                }
                this.interfaceLogDao.save(log);
                // 接口返回数据生成文件
                this.generateFile(Utils.white(log.getResp()), task.getStatus(), fileName, user, task.getId(), now, fileId);
                // OCR接口数据保存
                this.saveOcrData(respVo, taskId, task.getDeclId(), user, now);
            }
            // 组装邮件发送信息
            if (UserUtils.getPara(task.getCreateBy(), Constants.User.PARA_UPLOAD_DRAFT_EMAIL)) {
                List<TnEmailInfo> infos = new ArrayList<>();
                TsUserInfo userInfo = this.userDao.findByUserNo(task.getCreateBy());
                // 手动新建的任务，显示任务对应的补充凭证号1和2
                String pdocNo = Utils.white(task.getPdocNo1()) + " / " + Utils.white(task.getPdocNo2()) + " / " + Utils.white(task.getPdocNo3());
                String titlePdocNo = pdocNo;
                // 与GTS凭证号关联的的任务在邮件内显示内部凭证号类型及对应号码
                String sourceBy = null;
                if (task.getSourceBy().equals(Constants.Dtask.SOURCE_BY_GTS)) {
                    pdocNo = ListUtil.getPdocNo(task.getGtsInternalNo());
                    sourceBy = Constants.Dtask.SOURCE_BY_GTS;
                }
                if (StringUtil.isNotBlank(titlePdocNo)) {
                    titlePdocNo = "凭证:" + titlePdocNo + "; ";
                }
                String filepath = path + File.separator + fileName;

                if ("s12".equals(task.getTradeMode()) || "s15".equals(task.getTradeMode()) || "s16".equals(task.getTradeMode())) { //客户自提发送所有邮件通知用户于一封邮件内,不包含其他联系人
                    DeclTaskUserEmailVo emails = this.declTaskEmailService.getAllDeclTaskEmailList(task, Constants.User.PARA_UPLOAD_DRAFT_EMAIL, true);
                    String trademodel = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
                    if (StringUtil.isNotBlank(trademodel)) {
                        trademodel = trademodel.substring(trademodel.length() - 4);
                    }
                    if (Utils.isNotEmpty(emails.getAll())) {
                        String em = "";
                        for (UserEmailVo vo : emails.getAll()) {
                            if (StringUtil.isNotBlank(vo.getEmail())) {
                                if (vo.getId() != null) {
                                    if ("".equals(em)) {
                                        em = vo.getEmail();
                                    } else {
                                        em = em + ";" + vo.getEmail();
                                    }
                                }
                            }
                        }
                        TnEmailInfo info = new TnEmailInfo();
                        info.setRecipient(em);
                        info.setLang("zh");
                        info.setAttachment(filepath);
                        info.setAttachmentFilename(fileName);
                        info.setTitle(Utils.white(trademodel) + "," + titlePdocNo + "任务：" + task.getTaskNo() + " eCustoms:  校对稿已上传");
                        info.setTemplate("picked_up_declaration_draft_upload.zh");
                        info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                        info.put("name", userInfo.getName());
                        info.put("taskNo", task.getTaskNo());
                        info.put("taskUrl", DeclUtils.getTaskUrl(task));
                        info.put("serialNumber", task.getDeclaranceSerialNumber());
                        info.put("declUrl", DeclUtils.getDeclUrl(task));
                        info.put("createBy", user.getName());
                        info.put("company", UserUtils.getBuName(user));
                        info.put("createTime", DateUtils.fmtLongDate(now));
                        info.put("pdocNo", pdocNo);
                        info.put("sourceBy", sourceBy);
                        infos.add(info);
                    }
                } else {
                    List<String> mails = EmailUtil.getEmails(userInfo.getEmail());
                    // 任务转派用户
                    Set<Long> transUser = Utils.toSet(this.transferUserDao.getUserIdListByTaskId(task.getId()));
                    // 任务关联用户
                    Set<Long> users = Utils.toSet(this.userDeclarationTaskDao.getUserIdListByTaskId(task.getId()));
                    users.addAll(transUser);
                    // 邮件开关
                    users = UserUtils.filter(users, Constants.User.PARA_UPLOAD_DRAFT_EMAIL);

                    List<TsUserInfo> userInfos = this.userDao.findAllByIdIn(users);
                    List<TsUserInfo> mail = new ArrayList<>();
                    Boolean isSolvy = false;
                    if (Utils.isNotEmpty(userInfos)) {
                        for (TsUserInfo info : userInfos) {
                            if ("U".equals(info.getType())) {
                                mail.add(info);
                                isSolvy = true;
                            }
                        }
                        if (isSolvy) {
                            // 转派其他用户
                            List<String> transEmails = this.transferDao.findEmailByTaskId(task.getId());
                            for (int i = 0; i < transEmails.size(); i++) {
                                TsUserInfo vo = new TsUserInfo();
                                vo.setName(transEmails.get(i));
                                vo.setEmail(transEmails.get(i));
                                mail.add(vo);
                            }
                        }
                    }
                    mail.add(userInfo);
                    if (Utils.isNotEmpty(mail)) {
                        for (TsUserInfo vo : mail) {
                            TnEmailInfo info = new TnEmailInfo();
                            info.setRecipient(vo.getEmail());
                            info.setLang("zh");
                            info.setAttachment(filepath);
                            info.setAttachmentFilename(fileName);
                            info.setTitle(titlePdocNo + "任务：" + task.getTaskNo() + " eCustoms:  校对稿已上传");
                            info.setTemplate("declaration_draft_upload.zh");
                            info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                            info.put("name", vo.getName());
                            info.put("taskNo", task.getTaskNo());
                            info.put("taskUrl", DeclUtils.getTaskUrl(task));
                            info.put("serialNumber", task.getDeclaranceSerialNumber());
                            info.put("declUrl", DeclUtils.getDeclUrl(task));
                            info.put("createBy", user.getName());
                            info.put("company", UserUtils.getBuName(user));
                            info.put("createTime", DateUtils.fmtLongDate(now));
                            info.put("pdocNo", pdocNo);
                            info.put("sourceBy", sourceBy);
                            infos.add(info);
                        }
                    }
                }
                EmailStoreMq.putAll(infos);
            }
        }

        return valid;
    }

    private void sendEmailToMeihua(String path, String fileName, String taskNo, String msg) {
        TnEmailInfo info = new TnEmailInfo();
        info.setLang("zh");
        info.setRecipient("servicemail@metinform.cn");
        info.setAttachment(path + File.separatorChar + fileName);
        info.setAttachmentFilename(fileName);
        info.setTitle("索尔维- OCR识别异常-任务编号 " + taskNo);
        info.setTemplate("ocr_exception.zh");
        info.put("ocrData", msg);
        EmailStoreMq.put(info);
    }

    /**
     * OCR返回数据生成文件
     *
     * @param data       文件内容
     * @param taskStatus 任务状态
     * @param fileName   文件名
     * @param user       用户
     * @param taskId     任务ID
     * @param now        时间
     * @param sendId     重新发送文件ID
     */
    private void generateFile(String data, String taskStatus, String fileName, LoginUser user, Long taskId, Date now, Long sendId) {
        fileName = fileName.substring(0, fileName.lastIndexOf(".pdf")); // 文件前缀
        String folder = FileUtil.createPath(DictUtils.getValue(Constants.DictType.FOLDER, Constants.Folder.DECL_OCR_RESP));
        FileUtil.createDir(folder);
        String newName = "OCR_" + fileName + ".txt";
        String path = folder + File.separator + newName;
        FileUtil.write(path, data, "utf-8");
        Long fileId = FileRecordUtils.save(newName, folder, Constants.FILE_RECORD_TYPE_SYSTEM, "OCR接口返回数据", user.getUserNo());
        TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
        status.setTaskId(taskId);
        status.setStatus(taskStatus);
        status.setOperation(Constants.Dtask.SYS_OPER_ACCEPT_OCR);
        status.setLinkId(fileId);
        status.setLinkContent(newName);
        status.setLinkId2(sendId);
        status.setLinkContent2(fileName);
        status.setIsRead(Constants.NO);
        DeclUtils.setTaskStatusCompany(status, user);
        status.init(user, now);
        this.declarationTaskStatusDao.save(status);
    }

    /**
     * <p>Description: 附件重名重命名</p>
     *
     * @param i           index
     * @param fileNewName 新文件名
     * @param fileOldName 原文件名
     * @return 文件名
     * @Author Gu.Haiqiang
     * @Date: 2019/12/2
     */
    public String fileRename(int i, String fileNewName, List<String> fileOldName) {
        for (; i <= fileOldName.size(); i++) {
            fileNewName = fileNewName.substring(0, fileNewName.lastIndexOf("(")) // 文件前缀名
                    + "(" + i + ")." + fileNewName.substring(fileNewName.lastIndexOf(".") + 1);
            if (fileOldName.contains(fileNewName)) {
                this.fileRename(i + 1, fileNewName, fileOldName);
            } else {
                return fileNewName;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public String uploadTxblForm(List<MultipartFile> parts, Long listId, Long headId, Long taskId, String docType, LoginUser user) throws IOException {
        try {
            List<ToDocument> document = this.toDocumentDao.findByTaskId(taskId); // 根据任务id获取随附单据;
            // 定义list保存随附单据的文件名称
            List<String> documentNames = new ArrayList<>();
            if (Utils.isNotEmpty(document)) {
                for (ToDocument name : document) {
                    documentNames.add(name.getDocFileName());
                }
            }
            // 遍历上传文件list,如果存在则为文件重命名
            List<String> fileNewName = new ArrayList<>(); // 定义list保存上传文件的文件名
            if (Utils.isNotEmpty(parts)) {
                for (MultipartFile part : parts) {
                    if (documentNames.contains(part.getOriginalFilename())) {
                        String fileName = part.getOriginalFilename().substring(0, part.getOriginalFilename().lastIndexOf(".")); // 文件前缀
                        String filetype = part.getOriginalFilename().substring(part.getOriginalFilename().lastIndexOf(".") + 1); // 文件后缀
                        String newFileName = fileName + "(" + 1 + ")." + filetype;
                        if (!documentNames.contains(newFileName)) {
                            fileNewName.add(newFileName);
                        } else if (documentNames.contains(newFileName)) {
                            fileNewName.add(this.fileRename(1, newFileName, documentNames));
                        }
                    } else {
                        fileNewName.add(part.getOriginalFilename());
                    }
                }
            }
            if (Utils.isNotEmpty(parts)) {
                String path = DictUtils.getValue(Constants.DictType.FOLDER, Constants.Folder.DOCUMENT_UPLOAD);
                String newPath = null;
                Date now = new Date();
                TdDeclarationTask td = this.declarationTaskDao.get(taskId);
                List<String> files = new ArrayList<>();
                String docpath = null;
                String docNo = "";
                for (int i = 0; i < parts.size(); i++) {
                    newPath = FileUtil.createPath(path);
                    docNo = DateUtils.fmtDate8(new Date()) + Utils.randomNumber(6);
                    DocumentUtils.uploadDocument(parts.get(i), fileNewName.get(i), newPath, null, taskId, docNo, docType, false, "T");
                    Long fileId = FileRecordUtils.save(fileNewName.get(i), newPath, Constants.FILE_RECORD_TYPE_UPLOAD, "import.to.document", user.getUserNo());
                    files.add(newPath + File.separator + fileNewName.get(i));

                    // 任务记录新增动作
                    if (taskId != null) {
                        TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
                        status.init(user, now);
                        status.setTaskId(taskId);
                        status.setOperation(Constants.Dtask.OPER_UPLOAD_TXBL);
                        status.setLinkId(fileId);
                        status.setLinkContent(fileNewName.get(i));
                        status.setIsRead(Constants.NO);
                        DeclUtils.setTaskStatusCompany(status, user);
                        this.declarationTaskStatusDao.save(status);
                    }
                }
                // 发送邮件通知
                TdDeclarationTask task = this.declarationTaskDao.get(taskId);

                //生成分拨付税明细excel
                QueryVo qv = new QueryVo();
                qv.setLanguage("zh");
                qv.setId1(taskId);
                String finlePath = this.taxableExcel(qv, user);
                if (StringUtil.isNotBlank(finlePath)) {
                    files.add(finlePath);
                }


                //获取税单
                String filename = "";
                String zipPath = "";
                if (Utils.isNotEmpty(files)) {
                    if (files.size() > 1) {
                        String dir = FileUtil.createPath(DictUtils.getValue(Constants.DictType.FOLDER, Constants.Folder.DECL_ZIP));
                        FileUtil.createDir(dir);
                        zipPath = dir + File.separator + td.getTaskNo() + ".zip";
                        FileUtil.zip(zipPath, files);
                        filename = td.getTaskNo() + ".zip";
                    } else {
                        zipPath = newPath + File.separator + fileNewName.get(0);
                        filename = fileNewName.get(0);
                    }
                }

                // 组装邮件发送信息
                // 发送通知邮件通知所有关联该任务的人员
                // 任务关系用户
                List<Long> user1 = (List<Long>) this.userDeclarationTaskDao.getUserIdListByTaskId(taskId);
                // 转派用户
                List<Long> user2 = (List<Long>) this.transferUserDao.getUserIdListByTaskId(taskId);
                Set<Long> users = new HashSet<>();
                // 任务创建用户
                users.add(this.userDao.findIdByUserNo(td.getCreateBy()));
                users.add(user.getId()); // 当前操作附件替换用户
                users.addAll(user1); // 任务关系用户
                users.addAll(user2); // 转派用户

                //上传税单邮件接收开关
                users = UserUtils.filter(users, Constants.User.PARA_ADD_TAX_BILL_EMAIL);
                // 根据用户id获取邮箱
                List<TsUserInfo> list = this.userDao.findAllByIdIn(users);


                List<TnEmailInfo> infos = new ArrayList<>();
//                TsUserInfo userInfo = this.userDao.findByUserNo(task.getCreateBy());


                //获取字典邮箱
                List<TmDataDict> solvayEmials = this.dictDao.findByTypeIdOrderByCodeAsc(Constants.DictType.TAX_BILL_EMAIL);
                if (Utils.isNotEmpty(solvayEmials)) {
                    for (TmDataDict apemail : solvayEmials) {
                        TsUserInfo emailVo = new TsUserInfo();
                        emailVo.setName(apemail.getName());
                        emailVo.setEmail(apemail.getValue());
                        emailVo.setType(Constants.User.TYPE_NORMAL);
                        emailVo.setLanguage(Constants.LANGUAGE_ZH);
                        list.add(emailVo);
                    }
                }


                // 发送邮件
                String entryId = "";
                TdHead tdHead = this.headDao.get(task.getDeclId());
                if (tdHead != null) {
                    entryId = Utils.white(tdHead.getEntryId());
                }
                // 发送邮件
                if (Utils.isNotEmpty(list)) {
                    String em = "";
                    for (TsUserInfo vo : list) {
                        if (StringUtil.isNotBlank(vo.getEmail()) && vo.getType().equals(Constants.User.TYPE_NORMAL)) {
                            if ("".equals(em)) {
                                em = vo.getEmail();
                            } else {
                                em = em + ";" + vo.getEmail();
                            }
                        }
                    }
                    TnEmailInfo info = new TnEmailInfo();
                    info.setRecipient(em);
                    info.setLang("zh");
                    info.setAttachment(zipPath);
                    info.setAttachmentFilename(filename);
                    info.setTitle("5876 税单已上传 " + entryId + " /" + task.getTaskNo());
                    info.setTemplate("customs_tax_bill_upload.zh");
                    info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                    info.put("taskNo", task.getTaskNo());
                    info.put("taskUrl", DeclUtils.getTaskUrl(task));
                    info.put("serialNumber", task.getDeclaranceSerialNumber());
                    info.put("declUrl", DeclUtils.getDeclUrl(task));
                    info.put("createBy", user.getName());
                    info.put("company", UserUtils.getBuName(user));
                    info.put("createTime", DateUtils.fmtLongDate(now));
                    infos.add(info);
                }

                EmailStoreMq.putAll(infos);
            }
        } catch (Exception e) {
            Log.error(e);
            return OpResult.FAILED.getCode();
        }
        return OpResult.OK.getCode();
    }

    @Override
    @Transactional
    public String uploadDeclForm(MultipartFile file, Long listId, Long headId, Long taskId, String entryId, String docType, LoginUser user) throws IOException {
        Date now = new Date();
        String valid = DocumentUtils.verifyFile(file, docType);
        if (StringUtil.isBlank(valid)) {
            List<ToDocument> document = this.toDocumentDao.findByTaskId(taskId); // 根据任务id获取随附单据
            // 定义list保存随附单据的文件名称
            List<String> documentNames = new ArrayList<>();
            if (Utils.isNotEmpty(document)) {
                for (ToDocument name : document) {
                    documentNames.add(name.getDocFileName());
                }
            }
            // 遍历上传文件list,如果存在则为文件重命名
            String fileName = file.getOriginalFilename();
            if (documentNames.contains(fileName)) {
                String name = fileName.substring(0, fileName.lastIndexOf(".")); // 文件前缀
                String filetype = fileName.substring(fileName.lastIndexOf(".") + 1); // 文件后缀
                String newFileName = name + "(" + 1 + ")." + filetype;
                if (!documentNames.contains(newFileName)) {
                    fileName = newFileName;
                } else if (documentNames.contains(newFileName)) {
                    fileName = this.listService.fileRename(1, newFileName, documentNames);
                }
            } else {
                fileName = file.getOriginalFilename();
            }

            String path = FileUtil.createPath(DictUtils.getValue(Constants.DictType.FOLDER, Constants.Folder.DOCUMENT_UPLOAD));

            DocumentUtils.uploadDarftDocument(file, fileName, path, listId, taskId, entryId, docType, "C", false);
            Long fileId = FileRecordUtils.save(fileName, path, Constants.FILE_RECORD_TYPE_UPLOAD, "upload.customs.declaration", user.getUserNo());

            TdDeclarationTask task = this.declarationTaskDao.get(taskId);
            // 更新单证
            String channelName = this.collectCustStatusService.updateCustStatus(entryId, taskId, task.getDeclId());
            task.setChannelName(channelName);
            if (!task.isArchiveTask()) {
                task.setStatus(Constants.Dtask.STATUS_REVIEW_PENDING);
            }
            task.setApprovalMark(Constants.Dtask.APPROVAL_MARK_PASS);
            task.update(user, now);
            this.declarationTaskDao.save(task);

            TdHead head = this.headDao.get(task.getDeclId());
            head.setEntryId(entryId);
            head.setChannelName(channelName);
            if (!task.isArchiveTask()) {
                head.setStatus(Constants.IMP_STATUS_PRE_EXAMINE);
            }
            head.setSideStatus(Constants.IMP_STATUS_UPLOAD_SECOND);
            head.update(user, now);
            this.headDao.save(head);

            TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
            status.setTaskId(task.getId());
            status.setStatus(Constants.Dtask.STATUS_REVIEW_PENDING);
            if (task.isArchiveTask()) {
                status.setStatus(task.getStatus());
            }
            status.setOperation(Constants.Dtask.OPER_UPLOAD_SECOND);
            status.setLinkId(fileId);
            status.setLinkContent(fileName);
            status.setMsg(entryId);
            status.init(user, now);
            DeclUtils.setTaskStatusCompany(status, user);
            this.declarationTaskStatusDao.save(status);

            //修改出库单报关标识
//            List<TdOutboundOrder> outboundOrders = this.outboundOrderoDao.findByTaskId(task.getId());
//            if (Utils.isNotEmpty(outboundOrders)) {
//                for (TdOutboundOrder outboundOrder : outboundOrders) {
//                    outboundOrder.setDeclareMark(Constants.YES);
//                }
//                this.outboundOrderoDao.saveAll(outboundOrders);
//            }

            if (DeclUtils.email(task.getSourceBy()) && UserUtils.getPara(task.getCreateBy(), Constants.User.PARA_UPLOAD_DECL_EMAIL)) {
                // 组装邮件发送信息
                List<TnEmailInfo> infos = new ArrayList<>();
                TsUserInfo userInfo = this.userDao.findByUserNo(task.getCreateBy());
                // 手动新建的任务，显示任务对应的补充凭证号1和2
                String pdocNo = Utils.white(task.getPdocNo1()) + " / " + Utils.white(task.getPdocNo2()) + " / " + Utils.white(task.getPdocNo3());
                String titlePdocNo = pdocNo;
                // 与GTS凭证号关联的的任务在邮件内显示内部凭证号类型及对应号码
                String sourceBy = null;
                if (task.getSourceBy().equals(Constants.Dtask.SOURCE_BY_GTS)) {
                    pdocNo = ListUtil.getPdocNo(task.getGtsInternalNo());
                    sourceBy = Constants.Dtask.SOURCE_BY_GTS;
                }
                if (StringUtil.isNotBlank(titlePdocNo)) {
                    titlePdocNo = "凭证:" + titlePdocNo + "; ";
                }
                String filepath = path + File.separator + fileName;
                DeclTaskUserEmailVo emails = this.declTaskEmailService.getAllDeclTaskEmailList(task, Constants.User.PARA_UPLOAD_DECL_EMAIL, true);
                if ("s12".equals(task.getTradeMode()) || "s15".equals(task.getTradeMode()) || "s16".equals(task.getTradeMode())) { //客户自提发送所有邮件通知用户于一封邮件内
                    String trademodel = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
                    if (StringUtil.isNotBlank(trademodel)) {
                        trademodel = trademodel.substring(trademodel.length() - 4);
                    }
                    if (Utils.isNotEmpty(emails.getAll())) {
                        String em = "";
                        for (UserEmailVo vo : emails.getAll()) {
                            if (StringUtil.isNotBlank(vo.getEmail())) {
                                if (vo.getId() != null) {
                                    if ("".equals(em)) {
                                        em = vo.getEmail();
                                    } else {
                                        em = em + ";" + vo.getEmail();
                                    }
                                }
                            }
                        }
                        TnEmailInfo info = new TnEmailInfo();
                        info.setRecipient(em);
                        info.setLang("zh");
                        info.setAttachment(filepath);
                        info.setAttachmentFilename(fileName);
                        info.setTitle(Utils.white(trademodel) + "," + titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms:  出区单证已上传可以提货.");
                        info.setTemplate("picked_up_customs_declaration_upload.zh");
                        info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                        info.put("name", userInfo.getName());
                        info.put("taskNo", task.getTaskNo());
                        info.put("taskUrl", DeclUtils.getTaskUrl(task));
                        info.put("serialNumber", task.getDeclaranceSerialNumber());
                        info.put("declUrl", DeclUtils.getDeclUrl(task));
                        info.put("createBy", user.getName());
                        info.put("company", UserUtils.getBuName(user));
                        info.put("createTime", DateUtils.fmtLongDate(now));
                        info.put("pdocNo", pdocNo);
                        info.put("sourceBy", sourceBy);
                        infos.add(info);
                    }
                } else {
                    if (Utils.isNotEmpty(emails.getAll())) {
                        for (UserEmailVo vo : emails.getAll()) {
                            TnEmailInfo info = new TnEmailInfo();
                            info.setRecipient(vo.getEmail());
                            info.setLang("zh");
                            info.setAttachment(filepath);
                            info.setAttachmentFilename(fileName);
                            info.setTitle(titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms:  报关单已上传");
                            info.setTemplate("customs_declaration_upload.zh");
                            info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                            info.put("name", userInfo.getName());
                            info.put("taskNo", task.getTaskNo());
                            info.put("taskUrl", DeclUtils.getTaskUrl(task));
                            info.put("serialNumber", task.getDeclaranceSerialNumber());
                            info.put("declUrl", DeclUtils.getDeclUrl(task));
                            info.put("createBy", user.getName());
                            info.put("company", UserUtils.getBuName(user));
                            info.put("createTime", DateUtils.fmtLongDate(now));
                            info.put("pdocNo", pdocNo);
                            info.put("sourceBy", sourceBy);
                            infos.add(info);
                        }
                    }
                }
                EmailStoreMq.putAll(infos);
            }
        }
        return valid;
    }

    @Override
    public ImpVo getDeclInfo(Long id, LoginUser user) {
        ImpVo imp = this.headDao.findByIdOrderById(id);
        if (null != imp) {
            imp.setOwnerName(BuUtils.getName(imp.getOwnerId(), user.getLanguage()));
            imp.setDeptName(BuUtils.getName(imp.getDeptId(), user.getLanguage()));
            // 报关单证跳转类型判断
            TdDeclarationTask task = this.declarationTaskDao.findByDeclId(id);
            imp.setType(Utils.empty(task.getBusinessType()));
            imp.setListId(Utils.parseLong(task.getListId()));
        }
        return imp;
    }

    @Override
    @Transactional
    public String sendEmailForDeclDoc(Long taskId, LoginUser user) {
        Date now = new Date();
        TdDeclarationTask task = this.declarationTaskDao.get(taskId);
        TdHead head = this.headDao.get(task.getDeclId());
        head.setSideStatus(Constants.IMP_STATUS_SENT);

        TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
        status.setOperation(Constants.Dtask.OPER_SEND_GENERATE_NOTIFY);
        status.setStatus(task.getStatus());
        status.setTaskId(task.getId());
        status.setIsRead(Constants.NO);
        DeclUtils.setTaskStatusCompany(status, user);
        status.init(user, now);
        this.declarationTaskStatusDao.save(status);

        task.update(user, now);
        this.declarationTaskDao.save(task);

        head.update(user, now);
        this.headDao.save(head);

        // 发送邮件
        this.delcDocSendEmail(task, head, user, now);

        return null;
    }

    /**
     * <p>Description: 发送报文生成通知邮件</p>
     *
     * @param head 报关单
     * @param task 通关任务
     * @param user 当前用户
     * @param now  时间
     */
    private void delcDocSendEmail(TdDeclarationTask task, TdHead head, LoginUser user, Date now) {
        if (!DeclUtils.email(task.getSourceBy())) {
            return;
        }
        String url = DeclUtils.getMidUrl(head.getBusinessType()) + "/detail?id=" + head.getId() + "&tid=" + task.getId() + "&taskNo=" + task.getTaskNo();
        Set<Long> users = Utils.toSet(this.userDeclarationTaskDao.getUserIdListByTaskId(task.getId()));
        List<Long> transUser = (List<Long>) this.transferUserDao.getUserIdListByTaskId(task.getId());
        users.addAll(transUser);
        users = UserUtils.filter(users, Constants.User.PARA_SEND_DECL_DOC_EMAIL);

        List<UserEmailVo> emails = this.userDao.findByIdIn(users);
        // 手动新建的任务，显示任务对应的补充凭证号1和2
        String pdocNo = Utils.link(Constants.SLASH_WITH_SPACE, task.getPdocNo1(), task.getPdocNo2(), task.getPdocNo3());
        String titlePdocNo = pdocNo;
        // 与GTS凭证号关联的的任务在邮件内显示内部凭证号类型及对应号码
        String sourceBy = null;
        if (task.getSourceBy().equals(Constants.Dtask.SOURCE_BY_GTS)) {
            pdocNo = ListUtil.getPdocNo(task.getGtsInternalNo());
            sourceBy = Constants.Dtask.SOURCE_BY_GTS;
        }
        if (StringUtil.isNotBlank(titlePdocNo)) {
            titlePdocNo = "凭证:" + titlePdocNo + "; ";
        }
        List<TnEmailInfo> infos = new ArrayList<>();
        if ("s12".equals(task.getTradeMode()) || "s15".equals(task.getTradeMode()) || "s16".equals(task.getTradeMode())) { //客户自提发送所有邮件通知用户于一封邮件内(客户自提不通知其他联系人)
            DeclTaskUserEmailVo email = this.declTaskEmailService.getAllDeclTaskEmailList(task, Constants.User.PARA_SEND_DECL_DOC_EMAIL, true);
            String trademodel = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
            if (StringUtil.isNotBlank(trademodel)) {
                trademodel = trademodel.substring(trademodel.length() - 4);
            }
            if (Utils.isNotEmpty(email.getAll())) {
                List<String> att = this.setAttachment(task.getId());
                String em = "";
                for (UserEmailVo vo : email.getAll()) {
                    if (StringUtil.isNotBlank(vo.getEmail())) {
                        if (vo.getId() != null) {
                            if ("".equals(em)) {
                                em = vo.getEmail();
                            } else {
                                em = em + ";" + vo.getEmail();
                            }
                        }
                    }
                }
                TnEmailInfo info = new TnEmailInfo();
                info = new TnEmailInfo();
                info.setRecipient(em);
                info.setLang("zh");
                info.setTitle(Utils.white(trademodel) + "," + titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 报关单报文已生成");
                info.setTemplate("picked_up_decl_doc_report.zh");
                if (Utils.isNotEmpty(att)) {
                    info.setAttachment(att.get(0));
                    info.setAttachmentFilename(att.get(1));
                }
                info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                info.put("assignorCompany", UserUtils.getBuName(user));
                info.put("assignor", user.getName());
                info.put("taskNo", task.getTaskNo());
                info.put("taskUrl", DeclUtils.getTaskUrl(task));
                info.put("serialNumber", task.getDeclaranceSerialNumber());
                info.put("declUrl", DeclUtils.getDeclUrl(task));
                info.put("createTime", DateUtils.fmtLongDate(now));
                info.put("pdocNo", pdocNo);
                info.put("sourceBy", sourceBy);
                infos.add(info);
            }
        } else {
            List<UserEmailVo> list = this.getUserEmailList(emails, task.getEmail());
            if (Utils.isNotEmpty(list)) {
                List<String> att = this.setAttachment(task.getId());
                TnEmailInfo info = null;
                for (UserEmailVo vo : list) {
                    if (EmailUtil.validateEmail(vo.getEmail())) {
                        info = new TnEmailInfo();
                        info.setRecipient(vo.getEmail());
                        info.setLang(vo.getLanguage());
                        info.setTitle(titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 报关单报文已生成");
                        info.setTemplate("decl_doc_report.zh");
                        if (Utils.isNotEmpty(att)) {
                            info.setAttachment(att.get(0));
                            info.setAttachmentFilename(att.get(1));
                        }
                        info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                        info.put("name", vo.getName());
                        info.put("assignorCompany", UserUtils.getBuName(user));
                        info.put("assignor", user.getName());
                        info.put("taskNo", task.getTaskNo());
                        info.put("taskUrl", DeclUtils.getTaskUrl(task));
                        info.put("serialNumber", task.getDeclaranceSerialNumber());
                        info.put("declUrl", DeclUtils.getDeclUrl(task));
                        info.put("createTime", DateUtils.fmtLongDate(now));
                        info.put("url", EmailLinkUtils.createUrl(url, vo.getId(), vo.getEmail(), user));
                        info.put("pdocNo", pdocNo);
                        info.put("sourceBy", sourceBy);
                        infos.add(info);
                    }
                }
            }
        }

        EmailStoreMq.putAll(infos);
    }

    /**
     * <p>Description: 设置附件信息</p>
     *
     * @param id 任务ID
     * @return 数据
     */
    private List<String> setAttachment(Long id) {

        List<String> list = new ArrayList<>();

        TdDeclarationTaskStatus xmlStatus = this.declarationTaskStatusDao.getLastTaskStatusByTaskIdAndOperation(id, Constants.Dtask.OPER_GENERATE_XML);
        TlFileRecord xml = null;
        TlFileRecord excel = null;
        if (null != xmlStatus && null != xmlStatus.getLinkId()) {
            xml = this.fileRecordDao.get(xmlStatus.getLinkId());
        }
        TdDeclarationTaskStatus excelStatus = this.declarationTaskStatusDao.getLastTaskStatusByTaskIdAndOperation(id, Constants.Dtask.OPER_GENERATE_EXCEL);
        if (null != excelStatus && null != excelStatus.getLinkId()) {
            excel = this.fileRecordDao.get(excelStatus.getLinkId());
        }

        List<String> path = new ArrayList<>();
        List<String> name = new ArrayList<>();

        if (null != xml) {
            path.add(xml.getPath() + File.separator + xml.getFileName());
            name.add(xml.getFileName());
        }
        if (null != excel) {
            path.add(excel.getPath() + File.separator + excel.getFileName());
            name.add(excel.getFileName());
        }

        if (Utils.isNotEmpty(path)) {
            list.add(String.join(Constants.SEMICOLON, path));
            list.add(String.join(Constants.SEMICOLON, name));
        }
        return list;
    }

    @Override
    @Transactional
    public String customsCheck(Long taskId, String checkType, String checkRemarkText, LoginUser user) {
        Date now = new Date();
        TdDeclarationTask task = this.declarationTaskDao.get(taskId);
        TdHead head = this.headDao.get(task.getDeclId());

        List<Long> users = (List<Long>) this.userDeclarationTaskDao.getUserIdListByTaskId(taskId);
        List<Long> transUser = (List<Long>) this.transferUserDao.getUserIdListByTaskId(task.getId());
        users.addAll(transUser);
        users = Utils.toList(UserUtils.filter(users, Constants.User.PARA_DRAFT_CHECK_EMAIL));

        List<UserEmailVo> emails = this.userDao.findByIdIn(users);

        String url = DeclUtils.getMidUrl(head.getBusinessType()) + "/detail?id=" + head.getId() + "&tid=" + taskId + "&taskNo=" + task.getTaskNo();

        TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
        status.setIsRead(Constants.NO);
        status.setTaskId(task.getId());
        status.setLinkContent(checkRemarkText);
        DeclUtils.setTaskStatusCompany(status, user);
        status.init(user, now);
        // 更新附件审核状态
        this.toDocumentDao.updateDocByTaskAndHeadId(checkType, checkRemarkText, task.getId(), "P");

        if (Constants.YES.equals(checkType)) {
            // 通过
            head.setStatus(Constants.IMP_STATUS_PRE_EXAMINE);
            head.setSideStatus(Constants.IMP_STATUS_PRE_EXAMINE);

            status.setOperation(Constants.Dtask.OPER_APPRO_FIRST);
            status.setStatus(Constants.Dtask.STATUS_UPLOAD_FORM_PENDING);

            task.setApprovalMark(Constants.Dtask.APPROVAL_MARK_PASS);

            this.customsCheckPassEmail(emails, url, task, user, now, checkRemarkText);
            //初审通过后删除任务相关分拨付税预估表数据
            this.tdTaxableGtsItemEstimateDao.deleteByTaskId(task.getId());
        } else if (Constants.NO.equals(checkType)) {
            // 未通过
            head.setStatus(Constants.IMP_STATUS_DISPATCHED);
            head.setSideStatus(Constants.IMP_STATUS_DISPATCHED);

            status.setOperation(Constants.Dtask.OPER_REJECT_FIRST);
            status.setStatus(Constants.Dtask.STATUS_UPLOAD_DRAFT_PENDING);

            task.setApprovalMark(Constants.Dtask.APPROVAL_MARK_REJECT);

            this.customsCheckNotPassEmail(emails, url, task, user, now, checkRemarkText);
        }
        this.declarationTaskStatusDao.save(status);

        task.setStatus(status.getStatus());
        task.setUpdateBy(user.getUserNo());
        task.setUpdateTime(now);
        this.declarationTaskDao.save(task);

        head.setUpdateBy(user.getUserNo());
        head.setUpdateTime(now);
        this.headDao.save(head);

        return null;
    }

    @Override
    @Transactional
    public String customsRecheck(Long taskId, String checkType, String checkRemarkText, LoginUser user) {
        Date now = new Date();
        TdDeclarationTask task = this.declarationTaskDao.get(taskId);
        TdHead head = this.headDao.get(task.getDeclId());

        List<Long> users = (List<Long>) this.userDeclarationTaskDao.getUserIdListByTaskId(taskId);
        List<Long> transUser = (List<Long>) this.transferUserDao.getUserIdListByTaskId(task.getId());
        users.addAll(transUser);
        users = Utils.toList(UserUtils.filter(users, Constants.User.PARA_DRAFT_CHECK_EMAIL));

        List<UserEmailVo> emails = this.userDao.findByIdIn(users);

        String url = DeclUtils.getMidUrl(head.getBusinessType()) + "/detail?id=" + head.getId() + "&tid=" + taskId + "&taskNo=" + task.getTaskNo();

        TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
        status.setIsRead(Constants.NO);
        status.setTaskId(task.getId());
        status.setLinkContent(checkRemarkText);
        DeclUtils.setTaskStatusCompany(status, user);
        status.init(user, now);

        // 更新附件审核状态
        this.toDocumentDao.updateDocByTaskAndHeadId(checkType, checkRemarkText, task.getId(), "C");
        if (Constants.YES.equals(checkType)) {
            // 通过
            head.setStatus(Constants.IMP_STATUS_RE_CHECK);

            status.setOperation(Constants.Dtask.OPER_APPRO_SECOND);
            status.setStatus(Constants.Dtask.STATUS_COMPLETED);

            task.setReviewMark(Constants.Dtask.APPROVAL_MARK_PASS);

            this.customsRecheckPassEmail(emails, url, task, user, now, checkRemarkText);
        } else if (Constants.NO.equals(checkType)) {
            // 未通过
            head.setStatus(Constants.IMP_STATUS_DISPATCHED);
            head.setSideStatus(Constants.IMP_STATUS_DISPATCHED);

            status.setOperation(Constants.Dtask.OPER_REJECT_SECOND);
            status.setStatus(Constants.Dtask.STATUS_UPLOAD_FORM_PENDING);

            task.setReviewMark(Constants.Dtask.APPROVAL_MARK_REJECT);

            this.customsRecheckNotPassEmail(emails, url, task, user, now, checkRemarkText);
        }
        this.declarationTaskStatusDao.save(status);

        task.setStatus(status.getStatus());
        task.setUpdateBy(user.getUserNo());
        task.setUpdateTime(now);
        this.declarationTaskDao.save(task);

        head.setUpdateBy(user.getUserNo());
        head.setUpdateTime(now);
        this.headDao.save(head);

        return null;
    }

    @Override
    public String resetStatus(Long id, LoginUser user) {
        Date now = new Date();
        TdHead head = this.headDao.get(id);
        TdDeclarationTask task = this.declarationTaskDao.findByTaskNo(head.getTaskNo());

        // 更新附件审核状态
        this.toDocumentDao.updateDocByTask(Constants.NO, "报关单重置", task.getId());

        TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
        status.setStatus(Constants.Dtask.STATUS_INIT);
        status.setOperation(Constants.Dtask.OPER_RESET);
        status.setTaskId(task.getId());
        status.setIsRead(Constants.NO);
        DeclUtils.setTaskStatusCompany(status, user);
        status.init(user, now);
        this.declarationTaskStatusDao.save(status);

        task.setStatus(Constants.Dtask.STATUS_INIT);

        head.setStatus(Constants.IMP_STATUS_DISPATCHED);
        head.setSideStatus(Constants.IMP_STATUS_DISPATCHED);

        task.update(user, now);
        this.declarationTaskDao.save(task);

        head.update(user, now);
        this.headDao.save(head);
        return null;
    }

    @Override
    public String archiveStatus(Long id, LoginUser user) {
        Date now = new Date();
        TdHead head = this.headDao.get(id);
        if (Constants.Dtask.SOURCE_BY_ARCHIVED.equals(head.getSourceBy()) && !this.declarationTaskStatusDao.existsByTaskIdAndOperation(head.getTaskId(), Constants.Dtask.OPER_UPLOAD_SECOND)) {
            return "err.decl.need_upload_decl_first";
        } else {
            head.setStatus(Constants.IMP_STATUS_ARCHIVE);
            head.update(user, now);

            TdDeclarationTask task = this.declarationTaskDao.findByTaskNo(head.getTaskNo());

            TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
            status.setStatus(Constants.Dtask.STATUS_ARCHIVED);
            status.setOperation(Constants.Dtask.OPER_ARCHIVE);
            status.setTaskId(task.getId());
            status.setIsRead(Constants.NO);
            DeclUtils.setTaskStatusCompany(status, user);
            status.init(user, now);
            this.declarationTaskStatusDao.save(status);

            task.setStatus(Constants.Dtask.STATUS_ARCHIVED);
            task.update(user, now);
            this.declarationTaskDao.save(task);

            this.headDao.save(head);
            return null;
        }
    }

    /**
     * <p>Description: 初审通过</p>
     *
     * @param emails 用户邮件信息
     * @param url    邮件内容中URL
     * @param task   通关任务
     * @param user   当前用户
     * @param now    时间
     * @param remark 备注
     */
    private void customsCheckPassEmail(List<UserEmailVo> emails, String url, TdDeclarationTask task, LoginUser user, Date now, String remark) {
        if (!DeclUtils.email(task.getSourceBy())) {
            return;
        }
        List<TnEmailInfo> infos = new ArrayList<>();

        // 手动新建的任务，显示任务对应的补充凭证号1和2
        String pdocNo = Utils.link(Constants.SLASH_WITH_SPACE, task.getPdocNo1(), task.getPdocNo2(), task.getPdocNo3());
        String titlePdocNo = pdocNo;
        // 与GTS凭证号关联的的任务在邮件内显示内部凭证号类型及对应号码
        String sourceBy = null;
        if (task.getSourceBy().equals(Constants.Dtask.SOURCE_BY_GTS)) {
            pdocNo = ListUtil.getPdocNo(task.getGtsInternalNo());
            sourceBy = Constants.Dtask.SOURCE_BY_GTS;
        }
        if (StringUtil.isNotBlank(titlePdocNo)) {
            titlePdocNo = "凭证:" + titlePdocNo + "; ";
        }
        if ("s12".equals(task.getTradeMode()) || "s15".equals(task.getTradeMode()) || "s16".equals(task.getTradeMode())) { //客户自提发送所有邮件通知用户于一封邮件内
            DeclTaskUserEmailVo email = this.declTaskEmailService.getAllDeclTaskEmailList(task, Constants.User.PARA_DRAFT_CHECK_EMAIL, true);
            String trademodel = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
            if (StringUtil.isNotBlank(trademodel)) {
                trademodel = trademodel.substring(trademodel.length() - 4);
            }
            if (Utils.isNotEmpty(email.getAll())) {
                String em = "";
                for (UserEmailVo vo : email.getAll()) {
                    if (StringUtil.isNotBlank(vo.getEmail())) {
                        if (vo.getId() != null) {
                            if ("".equals(em)) {
                                em = vo.getEmail();
                            } else {
                                em = em + ";" + vo.getEmail();
                            }
                        }
                    }
                }
                TnEmailInfo info = new TnEmailInfo();
                info.setRecipient(em);
                info.setLang("zh");
                info.setTitle(Utils.white(trademodel) + "," + titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 校对稿初审已通过");
                info.setTemplate("picked_up_declaration_draft_approve.zh");
                info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                info.put("assignorCompany", UserUtils.getBuName(user));
                info.put("assignor", user.getName());
                info.put("taskNo", task.getTaskNo());
                info.put("taskUrl", DeclUtils.getTaskUrl(task));
                info.put("serialNumber", task.getDeclaranceSerialNumber());
                info.put("declUrl", DeclUtils.getDeclUrl(task));
                info.put("createTime", DateUtils.fmtLongDate(now));
                info.put("comment", Utils.white(remark));
                info.put("pdocNo", pdocNo);
                info.put("sourceBy", sourceBy);
                infos.add(info);
            }
        } else {
            List<UserEmailVo> list = this.getUserEmailList(emails, task.getEmail());
            if (Utils.isNotEmpty(list)) {
                TnEmailInfo info = null;
                for (UserEmailVo vo : list) {
                    if (EmailUtil.validateEmail(vo.getEmail())) {
                        info = new TnEmailInfo();
                        info.setRecipient(vo.getEmail());
                        info.setLang(vo.getLanguage());
                        info.setTitle(titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 校对稿初审已通过");
                        info.setTemplate("declaration_draft_approve.zh");
                        info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                        info.put("assignorCompany", UserUtils.getBuName(user));
                        info.put("name", vo.getName());
                        info.put("assignor", user.getName());
                        info.put("taskNo", task.getTaskNo());
                        info.put("taskUrl", DeclUtils.getTaskUrl(task));
                        info.put("serialNumber", task.getDeclaranceSerialNumber());
                        info.put("declUrl", DeclUtils.getDeclUrl(task));
                        info.put("createTime", DateUtils.fmtLongDate(now));
                        info.put("comment", Utils.white(remark));
                        info.put("url", EmailLinkUtils.createUrl(url, vo.getId(), vo.getEmail(), user));
                        info.put("pdocNo", pdocNo);
                        info.put("sourceBy", sourceBy);
                        infos.add(info);
                    }
                }
            }
        }

        EmailStoreMq.putAll(infos);
    }

    /**
     * <p>Description: 初审未通过</p>
     *
     * @param emails 用户邮件信息
     * @param url    邮件内容中URL
     * @param task   通关任务
     * @param user   当前用户
     * @param now    时间
     * @param remark 备注
     */
    private void customsCheckNotPassEmail(List<UserEmailVo> emails, String url, TdDeclarationTask task, LoginUser user, Date now, String remark) {
        if (!DeclUtils.email(task.getSourceBy())) {
            return;
        }
        List<TnEmailInfo> infos = new ArrayList<>();
        // 手动新建的任务，显示任务对应的补充凭证号1和2
        String pdocNo = Utils.link(Constants.SLASH_WITH_SPACE, task.getPdocNo1(), task.getPdocNo2(), task.getPdocNo3());
        String titlePdocNo = pdocNo;
        // 与GTS凭证号关联的的任务在邮件内显示内部凭证号类型及对应号码
        String sourceBy = null;
        if (task.getSourceBy().equals(Constants.Dtask.SOURCE_BY_GTS)) {
            pdocNo = ListUtil.getPdocNo(task.getGtsInternalNo());
            sourceBy = Constants.Dtask.SOURCE_BY_GTS;
        }
        if (StringUtil.isNotBlank(titlePdocNo)) {
            titlePdocNo = "凭证:" + titlePdocNo + "; ";
        }
        if ("s12".equals(task.getTradeMode()) || "s15".equals(task.getTradeMode()) || "s16".equals(task.getTradeMode())) { //客户自提发送所有邮件通知用户于一封邮件内(客户自提不通知其他联系人)
            DeclTaskUserEmailVo email = this.declTaskEmailService.getAllDeclTaskEmailList(task, Constants.User.PARA_DRAFT_CHECK_EMAIL, true);
            String trademodel = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
            if (StringUtil.isNotBlank(trademodel)) {
                trademodel = trademodel.substring(trademodel.length() - 4);
            }
            if (Utils.isNotEmpty(email.getAll())) {
                String em = "";
                for (UserEmailVo vo : email.getAll()) {
                    if (StringUtil.isNotBlank(vo.getEmail())) {
                        if (vo.getId() != null) {
                            if ("".equals(em)) {
                                em = vo.getEmail();
                            } else {
                                em = em + ";" + vo.getEmail();
                            }
                        }
                    }
                }
                TnEmailInfo info = new TnEmailInfo();
                info.setRecipient(em);
                info.setLang("zh");
                info.setTitle(Utils.white(trademodel) + "," + titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 校对稿初审未通过");
                info.setTemplate("picked_up_declaration_draft_reject.zh");
                info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                info.put("assignorCompany", UserUtils.getBuName(user));
                info.put("assignor", user.getName());
                info.put("taskNo", task.getTaskNo());
                info.put("taskUrl", DeclUtils.getTaskUrl(task));
                info.put("serialNumber", task.getDeclaranceSerialNumber());
                info.put("declUrl", DeclUtils.getDeclUrl(task));
                info.put("createTime", DateUtils.fmtLongDate(now));
                info.put("comment", Utils.white(remark));
                info.put("pdocNo", pdocNo);
                info.put("sourceBy", sourceBy);
                infos.add(info);
            }
        } else {
            List<UserEmailVo> list = this.getUserEmailList(emails, task.getEmail());
            // 任务转派用户
            if (Utils.isNotEmpty(list)) {
                TnEmailInfo info = null;
                for (UserEmailVo vo : list) {
                    if (EmailUtil.validateEmail(vo.getEmail())) {
                        info = new TnEmailInfo();
                        info.setRecipient(vo.getEmail());
                        info.setLang(vo.getLanguage());
                        info.setTitle(titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 校对稿初审未通过");
                        info.setTemplate("declaration_draft_reject.zh");
                        info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                        info.put("assignorCompany", UserUtils.getBuName(user));
                        info.put("name", vo.getName());
                        info.put("assignor", user.getName());
                        info.put("taskNo", task.getTaskNo());
                        info.put("taskUrl", DeclUtils.getTaskUrl(task));
                        info.put("serialNumber", task.getDeclaranceSerialNumber());
                        info.put("declUrl", DeclUtils.getDeclUrl(task));
                        info.put("createTime", DateUtils.fmtLongDate(now));
                        info.put("comment", Utils.white(remark));
                        info.put("url", EmailLinkUtils.createUrl(url, vo.getId(), vo.getEmail(), user));
                        info.put("pdocNo", pdocNo);
                        info.put("sourceBy", sourceBy);
                        infos.add(info);
                    }
                }
            }
        }

        EmailStoreMq.putAll(infos);

    }

    /**
     * <p>Description: 复审通过</p>
     *
     * @param emails 用户邮件信息
     * @param url    邮件内容中URL
     * @param task   通关任务
     * @param user   当前用户
     * @param now    时间
     * @param remark 备注
     */
    private void customsRecheckPassEmail(List<UserEmailVo> emails, String url, TdDeclarationTask task, LoginUser user, Date now, String remark) {
        if (!DeclUtils.email(task.getSourceBy())) {
            return;
        }
        List<TnEmailInfo> infos = new ArrayList<>();

        // 手动新建的任务，显示任务对应的补充凭证号1和2
        String pdocNo = Utils.link(Constants.SLASH_WITH_SPACE, task.getPdocNo1(), task.getPdocNo2(), task.getPdocNo3());
        String titlePdocNo = pdocNo;
        // 与GTS凭证号关联的的任务在邮件内显示内部凭证号类型及对应号码
        String sourceBy = null;
        if (task.getSourceBy().equals(Constants.Dtask.SOURCE_BY_GTS)) {
            pdocNo = ListUtil.getPdocNo(task.getGtsInternalNo());
            sourceBy = Constants.Dtask.SOURCE_BY_GTS;
        }
        if (StringUtil.isNotBlank(titlePdocNo)) {
            titlePdocNo = "凭证:" + titlePdocNo + "; ";
        }
        if ("s12".equals(task.getTradeMode()) || "s15".equals(task.getTradeMode()) || "s16".equals(task.getTradeMode())) { //客户自提发送所有邮件通知用户于一封邮件内(客户自提不通知其他联系人)
            DeclTaskUserEmailVo email = this.declTaskEmailService.getAllDeclTaskEmailList(task, Constants.User.PARA_DECL_CHECK_EMAIL, true);
            String trademodel = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
            if (StringUtil.isNotBlank(trademodel)) {
                trademodel = trademodel.substring(trademodel.length() - 4);
            }
            if (Utils.isNotEmpty(email.getAll())) {
                String em = "";
                for (UserEmailVo vo : email.getAll()) {
                    if (StringUtil.isNotBlank(vo.getEmail())) {
                        if (vo.getId() != null) {
                            if ("".equals(em)) {
                                em = vo.getEmail();
                            } else {
                                em = em + ";" + vo.getEmail();
                            }
                        }
                    }
                }
                TnEmailInfo info = new TnEmailInfo();
                info.setRecipient(em);
                info.setLang("zh");
                info.setTitle(Utils.white(trademodel) + "," + titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 报关单复审已通过");
                info.setTemplate("picked_up_customs_declaration_approve.zh");
                info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                info.put("assignorCompany", UserUtils.getBuName(user));
                info.put("assignor", user.getName());
                info.put("taskNo", task.getTaskNo());
                info.put("taskUrl", DeclUtils.getTaskUrl(task));
                info.put("serialNumber", task.getDeclaranceSerialNumber());
                info.put("declUrl", DeclUtils.getDeclUrl(task));
                info.put("createTime", DateUtils.fmtLongDate(now));
                info.put("comment", Utils.white(remark));
                info.put("pdocNo", pdocNo);
                info.put("sourceBy", sourceBy);
                infos.add(info);
            }
        } else {
            List<UserEmailVo> list = this.getUserEmailList(emails, task.getEmail());
            if (Utils.isNotEmpty(list)) {
                TnEmailInfo info = null;
                for (UserEmailVo vo : list) {
                    if (EmailUtil.validateEmail(vo.getEmail())) {
                        info = new TnEmailInfo();
                        info.setRecipient(vo.getEmail());
                        info.setLang(vo.getLanguage());
                        info.setTitle(titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 报关单复审已通过");
                        info.setTemplate("customs_declaration_approve.zh");
                        info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                        info.put("assignorCompany", UserUtils.getBuName(user));
                        info.put("name", vo.getName());
                        info.put("assignor", user.getName());
                        info.put("taskNo", task.getTaskNo());
                        info.put("taskUrl", DeclUtils.getTaskUrl(task));
                        info.put("serialNumber", task.getDeclaranceSerialNumber());
                        info.put("declUrl", DeclUtils.getDeclUrl(task));
                        info.put("createTime", DateUtils.fmtLongDate(now));
                        info.put("comment", Utils.white(remark));
                        info.put("url", EmailLinkUtils.createUrl(url, vo.getId(), vo.getEmail(), user));
                        info.put("pdocNo", pdocNo);
                        info.put("sourceBy", sourceBy);
                        infos.add(info);
                    }
                }
            }
        }

        EmailStoreMq.putAll(infos);
    }

    /**
     * <p>Description: 复审未通过</p>
     *
     * @param emails 用户邮件信息
     * @param url    邮件内容中URL
     * @param task   通关任务
     * @param user   当前用户
     * @param now    时间
     * @param remark 备注
     */
    private void customsRecheckNotPassEmail(List<UserEmailVo> emails, String url, TdDeclarationTask task, LoginUser user, Date now, String remark) {
        if (!DeclUtils.email(task.getSourceBy())) {
            return;
        }
        List<TnEmailInfo> infos = new ArrayList<>();
        // 手动新建的任务，显示任务对应的补充凭证号1和2
        String pdocNo = Utils.link(Constants.SLASH_WITH_SPACE, task.getPdocNo1(), task.getPdocNo2(), task.getPdocNo3());
        String titlePdocNo = pdocNo;
        // 与GTS凭证号关联的的任务在邮件内显示内部凭证号类型及对应号码
        String sourceBy = null;
        if (task.getSourceBy().equals(Constants.Dtask.SOURCE_BY_GTS)) {
            pdocNo = ListUtil.getPdocNo(task.getGtsInternalNo());
            sourceBy = Constants.Dtask.SOURCE_BY_GTS;
        }
        if (StringUtil.isNotBlank(titlePdocNo)) {
            titlePdocNo = "凭证:" + titlePdocNo + "; ";
        }
        if ("s12".equals(task.getTradeMode()) || "s15".equals(task.getTradeMode()) || "s16".equals(task.getTradeMode())) { //客户自提发送所有邮件通知用户于一封邮件内(客户自提不通知其他联系人)
            DeclTaskUserEmailVo email = this.declTaskEmailService.getAllDeclTaskEmailList(task, Constants.User.PARA_DECL_CHECK_EMAIL, true);
            String trademodel = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
            if (StringUtil.isNotBlank(trademodel)) {
                trademodel = trademodel.substring(trademodel.length() - 4);
            }
            if (Utils.isNotEmpty(email.getAll())) {
                String em = "";
                for (UserEmailVo vo : email.getAll()) {
                    if (StringUtil.isNotBlank(vo.getEmail())) {
                        if (vo.getId() != null) {
                            if ("".equals(em)) {
                                em = vo.getEmail();
                            } else {
                                em = em + ";" + vo.getEmail();
                            }
                        }
                    }
                }
                TnEmailInfo info = new TnEmailInfo();
                info.setRecipient(em);
                info.setLang("zh");
                info.setTitle(Utils.white(trademodel) + "," + titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 报关单复审未通过");
                info.setTemplate("picked_up_customs_declaration_reject.zh");
                info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                info.put("assignorCompany", UserUtils.getBuName(user));
                info.put("assignor", user.getName());
                info.put("taskNo", task.getTaskNo());
                info.put("taskUrl", DeclUtils.getTaskUrl(task));
                info.put("serialNumber", task.getDeclaranceSerialNumber());
                info.put("declUrl", DeclUtils.getDeclUrl(task));
                info.put("createTime", DateUtils.fmtLongDate(now));
                info.put("comment", Utils.white(remark));
                info.put("pdocNo", pdocNo);
                info.put("sourceBy", sourceBy);
                infos.add(info);
            }
        } else {
            List<UserEmailVo> list = this.getUserEmailList(emails, task.getEmail());
            if (Utils.isNotEmpty(list)) {
                TnEmailInfo info = null;
                for (UserEmailVo vo : list) {
                    if (EmailUtil.validateEmail(vo.getEmail())) {
                        info = new TnEmailInfo();
                        info.setRecipient(vo.getEmail());
                        info.setLang(vo.getLanguage());
                        info.setTitle(titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 报关单复审未通过");
                        info.setTemplate("customs_declaration_reject.zh");
                        info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                        info.put("assignorCompany", UserUtils.getBuName(user));
                        info.put("name", vo.getName());
                        info.put("assignor", user.getName());
                        info.put("taskNo", task.getTaskNo());
                        info.put("taskUrl", DeclUtils.getTaskUrl(task));
                        info.put("serialNumber", task.getDeclaranceSerialNumber());
                        info.put("declUrl", DeclUtils.getDeclUrl(task));
                        info.put("createTime", DateUtils.fmtLongDate(now));
                        info.put("comment", Utils.white(remark));
                        info.put("url", EmailLinkUtils.createUrl(url, vo.getId(), vo.getEmail(), user));
                        info.put("pdocNo", pdocNo);
                        info.put("sourceBy", sourceBy);
                        infos.add(info);
                    }
                }
            }
        }

        EmailStoreMq.putAll(infos);
    }

    /**
     * <p>Description: 取得通关任务对应的用户邮箱信息</p>
     *
     * @param emails 邮箱列表
     * @param email  手动输入的邮箱
     * @return 数据
     */
    private List<UserEmailVo> getUserEmailList(List<UserEmailVo> emails, String email) {
        List<UserEmailVo> list = new ArrayList<>();
        if (Utils.isNotEmpty(emails)) {
            for (UserEmailVo vo : emails) {
                if (EmailUtil.isMulti(vo.getEmail())) {
                    List<String> temp = EmailUtil.getEmail(vo.getEmail());
                    for (String e : temp) {
                        list.add(new UserEmailVo(vo.getId(), vo.getName(), e.trim(), vo.getLanguage()));
                    }
                } else {
                    list.add(vo);
                }
            }
        }
        // 用户手动输入的邮箱
        if (StringUtil.isNotBlank(email)) {
            if (EmailUtil.isMulti(email)) {
                List<String> temp = EmailUtil.getEmail(email);
                for (String e : temp) {
                    list.add(new UserEmailVo(null, e, e, Constants.LANGUAGE_ZH));
                }
            } else {
                list.add(new UserEmailVo(null, email, email, Constants.LANGUAGE_ZH));
            }
        }
        return list;
    }

    @Override
    public HeadPreviewVo headPreview(long id, long taskId) {
        HeadPreviewVo vo = this.headDao.headPreview(id);
        // 随附单证及编号
        String documentInfo = document(taskId);
        vo.setDocumentInfo(documentInfo);
        // 集装箱号
        String containerInfo = container(id);
        vo.setContainerInfo(containerInfo);
        return vo;
    }

    /**
     * <p>Description: 随附单证及编号</p>
     *
     * @param taskId 任务id
     * @return 组装后的随附单证代码及编号
     */
    private String document(long taskId) {
        QueryVo qv = new QueryVo();
        qv.setId1(taskId);
        List<TiGtsDocument> list = this.gtsService.listDocument(qv);
        StringBuilder str = new StringBuilder();
        for (TiGtsDocument document : list) {
            if (null != document.getDocTypeDesc()) {
                str.append(document.getDocTypeDesc()).append(";");
            }
        }
        return str.toString();
    }

    /**
     * <p>Description: 集装箱号</p>
     *
     * @param headId 主表ID
     * @return 组装后的集装箱号
     */
    private String container(long headId) {
        QueryVo qv = new QueryVo();
        qv.setId(headId);
        List<TdContainer> list = this.listContainer(qv);
        StringBuilder str = new StringBuilder();
        for (TdContainer container : list) {
            str.append(container.getContainerNo()).append(";");
        }
        return str.toString();
    }

    @Override
    public List<HsItemPreviewVo> hsItemPreview(QueryVo qv) {
        List<HsItemPreviewVo> list = this.hsItemDao.hsItemPreview(qv);
        if (Utils.isNotEmpty(list)) {
            for (HsItemPreviewVo vo : list) {
                vo.setItemSpecModel(Utils.removeSplitter(this.itemAttributeDao.getAttributeByItemId(vo.getId())));
            }
        }
        // 如果商品明细没有达到6条数据，则添加空数据用以补充前端页面表格行
        HsItemPreviewVo hsItemPreviewVo = new HsItemPreviewVo();
        for (int i = list.size(); i < 6; i++) {
            if (list.size() > 0) {
                hsItemPreviewVo.setId(list.get(0).getId());
            } else {
                hsItemPreviewVo.setId(Long.parseLong(i + ""));
            }
            list.add(hsItemPreviewVo);
        }
        return list;
    }

    @Override
    public TdContainer findContainerByContainerNo(Long headId, String containerNo) {
        return this.containerDao.getContainerByHeadIdAndContainerNo(headId, containerNo);
    }

    @Override
    public List<HeadTemplateVo> searchHeadTemplateList(HeadTemplateQuery qv) {
        return this.headTemplateDao.searchHeadTemplateList(qv, UserUtils.getLoginUser());
    }

    @Override
    @Transactional
    public String addHeadTemplate(LoginUser user, HeadTemplateVo vo) throws InvocationTargetException, IllegalAccessException {
        Date now = new Date();
        String userNo = user.getUserNo();
        TdHead head = this.headDao.get(vo.getHeadId());
        // 新增报关单证主表
        TdHead tempHead = new TdHead();
        BeanUtils.copyProperties(tempHead, head);
        tempHead.setId(null);
        tempHead.setTempMark(Constants.YES);
        tempHead.setCreateTime(now);
        tempHead.setCreateBy(userNo);
        tempHead.setDeleteMark(Constants.NO);
        this.headDao.save(tempHead);
        // 新增报关单证模板表
        if (vo.getId() != null) {
            this.headTemplateDao.deleteById(vo.getId());
        }
        TdHeadTemplate template = new TdHeadTemplate();
        template.setHeadId(tempHead.getId());
        template.setTempName(vo.getTempName());
        template.setCreateTime(now);
        template.setCreateBy(userNo);
        template.setUpdateTime(now);
        template.setUpdateBy(userNo);
        template.setDeleteMark(Constants.NO);
        this.headTemplateDao.save(template);
        // 新增明细表
        TdDetail detail = this.detailDao.findByHeadId(vo.getHeadId());
        if (Utils.isNotNull(detail)) {
            TdDetail tempDetail = new TdDetail();
            BeanUtils.copyProperties(tempDetail, detail);
            tempDetail.setId(null);
            tempDetail.setHeadId(tempHead.getId());
            tempDetail.setCreateTime(now);
            tempDetail.setCreateBy(userNo);
            this.detailDao.save(tempDetail);
        }
        // 新增运输表
        TdTransportation trans = this.transportationDao.findByHeadId(vo.getHeadId());
        if (Utils.isNotNull(trans)) {
            TdTransportation tempTrans = new TdTransportation();
            BeanUtils.copyProperties(tempTrans, trans);
            tempTrans.setId(null);
            tempTrans.setHeadId(tempHead.getId());
            tempTrans.setCreateTime(now);
            tempTrans.setCreateBy(userNo);
            this.transportationDao.save(tempTrans);
        }
        // 新增伙伴表 - 境内收货人
        savePartnerTemp(vo.getHeadId(), Constants.Partner.DECLARANT, tempHead.getId(), userNo);
        // 新增伙伴表 - 消费使用单位, 货主公司
        savePartnerTemp(vo.getHeadId(), Constants.Partner.IMPORTER, tempHead.getId(), userNo);
        // 新增伙伴表 - 境外发货人
        savePartnerTemp(vo.getHeadId(), Constants.Partner.EXPORTER, tempHead.getId(), userNo);
        // 新增伙伴表 - 申报单位
        savePartnerTemp(vo.getHeadId(), Constants.Partner.REPRESENTATIVE_DECLARANT, tempHead.getId(), userNo);
        // 新增伙伴表 - 录入单位
        savePartnerTemp(vo.getHeadId(), Constants.Partner.COP, tempHead.getId(), userNo);
        // 新增费用
        TdFee fee = this.feeDao.findByHeadId(vo.getHeadId());
        if (Utils.isNotNull(fee)) {
            TdFee tempFee = new TdFee();
            BeanUtils.copyProperties(tempFee, fee);
            tempFee.setId(null);
            tempFee.setHeadId(tempHead.getId());
            tempFee.setCreateTime(now);
            tempFee.setCreateBy(userNo);
            this.feeDao.save(tempFee);
        }
        // 新增检验检疫
        TdInspectionQuarantine iq = this.inspectionQuarantineDao.findByHeadId(vo.getHeadId());
        if (Utils.isNotNull(iq)) {
            TdInspectionQuarantine tempIq = new TdInspectionQuarantine();
            BeanUtils.copyProperties(tempIq, iq);
            tempIq.setId(null);
            tempIq.setHeadId(tempHead.getId());
            tempIq.setCreateTime(now);
            tempIq.setCreateBy(userNo);
            this.inspectionQuarantineDao.save(tempIq);
        }
        // 新增使用单位
        createOrCopyConsumer(vo.getHeadId(), tempHead.getId(), now, userNo);
        // 企业资质
        createOrCopyEq(vo.getHeadId(), tempHead.getId(), now, userNo);
        // 新增集装箱
        createOrCopyContainer(vo.getHeadId(), tempHead.getId(), now, userNo);

        return null;
    }

    @Override
    @Transactional
    public String copyHeadTemplate(LoginUser user, Long headId, Long tempHeadId) throws InvocationTargetException, IllegalAccessException {
        Date now = new Date();
        String userNo = user.getUserNo();
        // 保存明细表
        TdDetail tempDetail = this.detailDao.findByHeadId(tempHeadId);
        if (Utils.isNotNull(tempDetail)) {
            TdDetail oldDetail = this.detailDao.findByHeadId(headId);
            TdDetail newDetail = new TdDetail();
            BeanUtils.copyProperties(newDetail, tempDetail);
            if (Utils.isNotNull(oldDetail)) { // 更新
                newDetail.setClientSeqNo(oldDetail.getClientSeqNo());
                newDetail.setBillSeqNo(oldDetail.getBillSeqNo());
                newDetail.setId(oldDetail.getId());
                newDetail.setHeadId(headId);
                newDetail.setUpdateTime(now);
                newDetail.setUpdateBy(userNo);
            } else { // 新增
                newDetail.setId(null);
                newDetail.setClientSeqNo(null);
                newDetail.setBillSeqNo(null);
                newDetail.setHeadId(headId);
                newDetail.setCreateTime(now);
                newDetail.setCreateBy(userNo);
            }
            this.detailDao.save(newDetail);
        }
        // 新增运输表
        TdTransportation tempTrans = this.transportationDao.findByHeadId(tempHeadId);
        if (Utils.isNotNull(tempTrans)) {
            TdTransportation oldTrans = this.transportationDao.findByHeadId(headId);
            TdTransportation newTrans = new TdTransportation();
            BeanUtils.copyProperties(newTrans, tempTrans);
            if (Utils.isNotNull(oldTrans)) { // 更新
                newTrans.setId(oldTrans.getId());
                newTrans.setHeadId(headId);
                newTrans.setUpdateTime(now);
                newTrans.setUpdateBy(userNo);
            } else { // 新增
                newTrans.setId(null);
                newTrans.setHeadId(headId);
                newTrans.setCreateTime(now);
                newTrans.setCreateBy(userNo);
            }
            this.transportationDao.save(newTrans);
        }
        // 新增伙伴表 - 境内收货人
        saveCopyPartner(headId, Constants.Partner.DECLARANT, tempHeadId, userNo);
        // 新增伙伴表 - 消费使用单位, 货主公司
        saveCopyPartner(headId, Constants.Partner.IMPORTER, tempHeadId, userNo);
        // 新增伙伴表 - 境外发货人
        saveCopyPartner(headId, Constants.Partner.EXPORTER, tempHeadId, userNo);
        // 新增伙伴表 - 申报单位
        saveCopyPartner(headId, Constants.Partner.REPRESENTATIVE_DECLARANT, tempHeadId, userNo);
        // 新增伙伴表 - 录入单位
        saveCopyPartner(headId, Constants.Partner.COP, tempHeadId, userNo);
        // 新增费用
        TdFee tempFee = this.feeDao.findByHeadId(tempHeadId);
        if (Utils.isNotNull(tempFee)) {
            TdFee oldFee = this.feeDao.findByHeadId(headId);
            TdFee newFee = new TdFee();
            BeanUtils.copyProperties(newFee, tempFee);
            if (Utils.isNotNull(oldFee)) {
                newFee.setId(oldFee.getId());
                newFee.setHeadId(headId);
                newFee.setUpdateTime(now);
                newFee.setUpdateBy(userNo);
            } else {
                newFee.setId(null);
                newFee.setHeadId(headId);
                newFee.setCreateTime(now);
                newFee.setCreateBy(userNo);
            }
            this.feeDao.save(newFee);
        }
        // 新增检验检疫
        TdInspectionQuarantine tempIq = this.inspectionQuarantineDao.findByHeadId(tempHeadId);
        if (Utils.isNotNull(tempIq)) {
            TdInspectionQuarantine oldIq = this.inspectionQuarantineDao.findByHeadId(headId);
            TdInspectionQuarantine newIq = new TdInspectionQuarantine();
            BeanUtils.copyProperties(newIq, tempIq);
            if (Utils.isNotNull(oldIq)) {
                newIq.setId(oldIq.getId());
                newIq.setHeadId(headId);
                newIq.setUpdateTime(now);
                newIq.setUpdateBy(userNo);
            } else {
                newIq.setId(null);
                newIq.setHeadId(headId);
                newIq.setCreateTime(now);
                newIq.setCreateBy(userNo);
            }
            this.inspectionQuarantineDao.save(newIq);
        }
        // 新增使用单位
        createOrCopyConsumer(tempHeadId, headId, now, userNo);
        // 企业资质
        createOrCopyEq(tempHeadId, headId, now, userNo);
        // 新增集装箱
        createOrCopyContainer(tempHeadId, headId, now, userNo);

        return null;
    }

    /**
     * <p>Description: 伙伴表-模板生成</p>
     *
     * @param oldHeadId  原主表ID
     * @param type       类型
     * @param tempHeadId 模板主表ID
     * @param userNo     用户号
     * @throws InvocationTargetException InvocationTargetException
     * @throws IllegalAccessException    IllegalAccessException
     */
    private void savePartnerTemp(Long oldHeadId, String type, Long tempHeadId, String userNo) throws InvocationTargetException, IllegalAccessException {
        TdPartner partner = this.partnerDao.findByHeadIdAndType(oldHeadId, type);
        if (Utils.isNotNull(partner)) {
            TdPartner tempPartner = new TdPartner();
            BeanUtils.copyProperties(tempPartner, partner);
            tempPartner.setId(null);
            tempPartner.setHeadId(tempHeadId);
            tempPartner.setCreateTime(new Date());
            tempPartner.setCreateBy(userNo);
            this.partnerDao.save(tempPartner);
        }
    }

    /**
     * <p>Description: 伙伴表-模板复制</p>
     *
     * @param headId     原主表ID
     * @param type       类型
     * @param tempHeadId 模板主表ID
     * @param userNo     用户号
     * @throws InvocationTargetException InvocationTargetException
     * @throws IllegalAccessException    IllegalAccessException
     */
    private void saveCopyPartner(Long headId, String type, Long tempHeadId, String userNo) throws InvocationTargetException, IllegalAccessException {
        TdPartner tempPartner = this.partnerDao.findByHeadIdAndType(tempHeadId, type);
        if (Utils.isNotNull(tempPartner)) {
            TdPartner oldPartner = this.partnerDao.findByHeadIdAndType(headId, type);
            TdPartner newPartner = new TdPartner();
            BeanUtils.copyProperties(newPartner, tempPartner);
            if (Utils.isNotNull(oldPartner)) {
                newPartner.setId(oldPartner.getId());
                newPartner.setHeadId(headId);
                newPartner.setUpdateTime(new Date());
                newPartner.setUpdateBy(userNo);
            } else {
                newPartner.setId(null);
                newPartner.setHeadId(headId);
                newPartner.setCreateTime(new Date());
                newPartner.setCreateBy(userNo);
            }
            this.partnerDao.save(newPartner);
        }
    }

    /**
     * <p>Description: 使用单位-模板生成、复制</p>
     *
     * @param oldHeadId 原主表ID
     * @param headId    主表ID
     * @param now       日期
     * @param userNo    用户号
     * @throws InvocationTargetException InvocationTargetException
     * @throws IllegalAccessException    IllegalAccessException
     */
    private void createOrCopyConsumer(Long oldHeadId, Long headId, Date now, String userNo) throws InvocationTargetException, IllegalAccessException {
        List<TdConsumer> tempConsumerList = this.consumerDao.findByHeadId(oldHeadId);
        this.consumerDao.deleteTdConsumerByHeadId(headId);
        if (Utils.isNotEmpty(tempConsumerList)) {
            for (TdConsumer tempConsumer : tempConsumerList) {
                TdConsumer newConsumer = new TdConsumer();
                BeanUtils.copyProperties(newConsumer, tempConsumer);
                newConsumer.setId(null);
                newConsumer.setHeadId(headId);
                newConsumer.setCreateBy(userNo);
                newConsumer.setCreateTime(now);
                this.consumerDao.save(newConsumer);
            }
        }
    }

    /**
     * <p>Description: 企业资质-模板生成、复制</p>
     *
     * @param oldHeadId 原主表ID
     * @param headId    主表ID
     * @param now       日期
     * @param userNo    用户号
     * @throws InvocationTargetException InvocationTargetException
     * @throws IllegalAccessException    IllegalAccessException
     */
    private void createOrCopyEq(Long oldHeadId, Long headId, Date now, String userNo) throws InvocationTargetException, IllegalAccessException {
        this.enterpriseQualificationDao.deleteTdEnterpriseQualificationByHeadId(headId);
        List<TdEnterpriseQualification> tempEqList = this.enterpriseQualificationDao.findByHeadId(oldHeadId);
        if (Utils.isNotEmpty(tempEqList)) {
            for (TdEnterpriseQualification eq : tempEqList) {
                TdEnterpriseQualification newEq = new TdEnterpriseQualification();
                BeanUtils.copyProperties(newEq, eq);
                newEq.setId(null);
                newEq.setHeadId(headId);
                newEq.setCreateBy(userNo);
                newEq.setCreateTime(now);
                this.enterpriseQualificationDao.save(newEq);
            }
        }
    }

    /**
     * 集装箱-模板生成、复制
     */
    /**
     * <p>Description: 集装箱-模板生成、复制</p>
     *
     * @param oldHeadId 原主表ID
     * @param headId    主表ID
     * @param now       日期
     * @param userNo    用户号
     * @throws InvocationTargetException InvocationTargetException
     * @throws IllegalAccessException    IllegalAccessException
     */
    private void createOrCopyContainer(Long oldHeadId, Long headId, Date now, String userNo) throws InvocationTargetException, IllegalAccessException {
        List<TdContainer> containerList = this.containerDao.findByHeadId(oldHeadId);
        if (Utils.isNotEmpty(containerList)) {
            for (TdContainer container : containerList) {
                TdContainer tempContainer = new TdContainer();
                BeanUtils.copyProperties(tempContainer, container);
                tempContainer.setId(null);
                tempContainer.setHeadId(headId);
                tempContainer.setCreateTime(now);
                tempContainer.setCreateBy(userNo);
                this.containerDao.save(tempContainer);
                // 新增商品与集装箱关系表
                List<TrItemContainer> itemContainers = this.itemContainerDao.findByContainerId(container.getId());
                List<Long> itemIds = new ArrayList<>();
                itemContainers.forEach(p -> itemIds.add(p.getItemId()));
                Long[] itemRelation = itemIds.toArray(new Long[itemIds.size()]);
                saveItemContainer(tempContainer.getId(), itemRelation, tempContainer.getCreateBy(), headId);
            }
        }
    }

    /**
     * <p>Description: 报关单预览</p>
     *
     * @param id     id
     * @param taskId 任务id
     * @return java.lang.String
     * @throws Exception Exception
     * @Author Gu.Haiqiang
     * @Date: 2019/9/9
     */
    @Override
    public List<String> previewCreatePdf(Long id, Long taskId) throws Exception {
        String type = this.previewsPdf(id, taskId);
        ToDocument toDocument = this.toDocumentDao.findFirstByTaskIdAndDocTypeOrderByCreateTimeDesc(taskId, type);
        List<String> list = new ArrayList<>();
        list.add(Utils.toString(toDocument.getId()));
        list.add(toDocument.getDocFileName());
        return list;
    }

    /**
     * <p>Description: 取得模板</p>
     *
     * @param deptId 部门ID
     * @param type   类型
     * @return TmDocumentTemplate
     */
    private TmDocumentTemplate getDocumentTemplate(Long deptId, String type) {
        TmDocumentTemplate temp = null;
        if (null != deptId) {
            temp = this.tmDocumentTemplateDao.findByDeptIdAndCode(deptId, type);
        }
        if (null == temp) {
            temp = this.tmDocumentTemplateDao.findByDeptIdIsNullAndCode(type);
        }
        return temp;
    }

    /**
     * <p>Description: 报关单预览文档生成</p>
     *
     * @param id     head id
     * @param taskId 任务id
     * @return java.lang.String
     * @throws Exception Exception
     * @Author Gu.Haiqiang
     * @Date: 2019/9/11
     */
    String previewsPdf(Long id, Long taskId) throws Exception {
        TdHead tdHead = this.headDao.findByIdAndTaskId(id, taskId);
        PreviewVo previewVo = new PreviewVo();
        TdDetail tdDetail = this.detailDao.findByHeadId(id);
        TdTransportation transportation = this.transportationDao.findByHeadId(id);
        TdFee tdFee = this.feeDao.findByHeadId(id);

        if (StringUtil.isNotBlank(tdDetail.getEntryCustoms())) {
            TpCustoms tpCustoms = this.tpCustomsDao.findByCode(tdDetail.getEntryCustoms());
            previewVo.setEntryCustomsName(Utils.space(tpCustoms.getName())); // 进境关别名称
        }
        previewVo.setEntryCustoms(Utils.space(tdDetail.getEntryCustoms())); // 进境关别

        if (StringUtil.isNotBlank(tdDetail.getDeclarationCustoms())) {
            TpCustoms tpCustoms = this.tpCustomsDao.findByCode(tdDetail.getDeclarationCustoms());
            previewVo.setDeclarationCustomsDesc(Utils.space(tpCustoms.getName())); // 申报地海关名称
        }
        previewVo.setDeclarationCustoms(Utils.space(tdDetail.getDeclarationCustoms())); // 申报地海关

        if (StringUtil.isNotBlank(transportation.getTradeNation())) {
            TpCountryRegin tpCountryRegin = this.tpCountryReginDao.findByCode(transportation.getTradeNation());
            previewVo.setTradeNationDesc(Utils.space(tpCountryRegin.getName()));  // 进境申报国/贸易国别名称
        }
        previewVo.setTradeNation(Utils.space(transportation.getTradeNation())); // 进境申报国/贸易国别

        if (StringUtil.isNotBlank(transportation.getTradeCountry())) {
            TpCountryRegin tpCountryRegin = this.tpCountryReginDao.findByCode(transportation.getTradeCountry());
            previewVo.setTradeCountryDesc(Utils.space(tpCountryRegin.getName())); // 目的国名称
        }
        previewVo.setTradeCountry(Utils.space(transportation.getTradeCountry())); // 目的国

        if (StringUtil.isNotBlank(transportation.getPod())) {
            TpPort tpPort = this.portDao.findByCode(transportation.getPod());
            if (tpPort != null) {
                previewVo.setPodDesc(Utils.space(tpPort.getName())); // 启运港名称
            }
        }
        previewVo.setPod(Utils.space(transportation.getPod())); // 启运港

        if (StringUtil.isNotBlank(transportation.getPoa())) {
            TpPort tpPort = this.portDao.findByCode(transportation.getPoa());
            if (tpPort != null) {
                previewVo.setPoaDesc(Utils.space(tpPort.getName())); // 经停港名称
            }
        }
        previewVo.setPoa(Utils.space(transportation.getPoa())); // 经停港

        if (StringUtil.isNotBlank(transportation.getArrivalLocation())) {
            TpDomesticPort tpDomesticPort = this.tpDomesticPortDao.findByCode(transportation.getArrivalLocation());
            previewVo.setArrivalLocationDesc(Utils.space(tpDomesticPort.getName())); // 入境口岸名称
        }
        previewVo.setArrivalLocation(Utils.space(transportation.getArrivalLocation())); // 入境口岸

        if (StringUtil.isNotBlank(tdDetail.getEntryCustoms())) {
            TpCustoms tpCustoms = this.tpCustomsDao.findByCode(tdDetail.getEntryCustoms());
            if (tpCustoms != null) {
                previewVo.setEntryCustomsName(Utils.space(tpCustoms.getName())); // 进境关别 -名称
            }
        }

        previewVo.setDeclaranceSerialNumber(Utils.space(tdHead.getDeclaranceSerialNumber())); // 通关流水号
        previewVo.setPreEntryId(Utils.space(tdDetail.getPreEntryId())); // 预录入编号
        previewVo.setEntryCustoms(Utils.space(tdDetail.getEntryCustoms())); // 进境关别
        // -
        // 编码
        Date ieDate = tdDetail.getIeDate();
        String importDate = null;
        if (ieDate != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); // Date指定格式：yyyy-MM-dd
            importDate = simpleDateFormat.format(ieDate);
        }
        previewVo.setImportDate(Utils.space(importDate)); // 进口日期

        previewVo.setRecordNumber(Utils.space(tdDetail.getRecordNumber())); // 备案号
        previewVo.setTransportType(Utils.space(tdDetail.getTransportType())); // 运输方式
        if (StringUtil.isNotBlank(tdDetail.getTransportType())) {
            previewVo.setTransportTypeDesc(Utils.space(DictUtils.getValue(Constants.DictType.P_TRANSPORTATION_MODE, tdDetail.getTransportType()))); // 运输方式名称
        }


        previewVo.setTransportName(Utils.space(tdDetail.getTransportName())); // 运输工具名称
        previewVo.setVoyageNo(Utils.space(tdDetail.getVoyageNo())); // 航次号
        previewVo.setBillNo(Utils.space(tdDetail.getBillNo())); // 提运单号
        previewVo.setCutMode(Utils.space(tdDetail.getCutMode())); // 征免性质
        if (StringUtil.isNotBlank(tdDetail.getCutMode())) {
            previewVo.setCutModeDesc(Utils.space(DictUtils.getValue(Constants.DictType.P_CUT_MODE, tdDetail.getCutMode()))); // 征免性质名称
        }


        previewVo.setLicenseKey(Utils.space(tdDetail.getLicenseKey())); // 许可证号
        previewVo.setContratNumber(Utils.space(tdDetail.getContratNumber())); // 合同协议号
        previewVo.setPackageType(Utils.space(tdDetail.getPackageType())); // 包装种类
        String packageTypeDesc = "";
        if (StringUtil.isNotBlank(tdDetail.getPackageType())) {
            packageTypeDesc = DictUtils.getValue(Constants.DictType.P_PACKAGE_TYPE, tdDetail.getPackageType());
        }
        previewVo.setPackageTypeDesc(Utils.space(packageTypeDesc)); // 包装种类名称
        //其他包装
        String otherPacking = "";
        String packageType = Utils.space(tdDetail.getPackageType());
        if (StringUtil.isNotBlank(tdDetail.getNoOtherPackaging()) && tdDetail.getNoOtherPackaging().equals("0")) {
            otherPacking = tdDetail.getOtherPackaging();
            if (StringUtil.isNotBlank(otherPacking)) {
                if (StringUtil.isNotBlank(tdDetail.getPackageType())) {
                    packageType = tdDetail.getPackageType() + "/" + otherPacking.replaceAll(",", "/");
                } else {
                    packageType = otherPacking.replaceAll(",", "/");
                }
                String[] list = otherPacking.split(",");
                for (int i = 0; i < list.length; i++) {
                    if (StringUtil.isNotBlank(packageTypeDesc)) {
                        packageTypeDesc = packageTypeDesc + "/" + DictUtils.getValue(Constants.DictType.P_PACKAGE_TYPE, list[i]);
                    } else {
                        packageTypeDesc = DictUtils.getValue(Constants.DictType.P_PACKAGE_TYPE, list[i]);
                    }

                }
            }
        }
        previewVo.setPackageType(packageType);
        previewVo.setPackageTypeDesc(packageTypeDesc);
        if (StringUtil.isNotBlank(tdDetail.getSpecialRelation())) {
            previewVo.setSpecialRelationDesc(Utils.space(DictUtils.getValue(Constants.DictType.SPEC_REL_CONFIRM, tdDetail.getSpecialRelation()))); // 特殊关系确认
        }

        if (StringUtil.isNotBlank(tdDetail.getPriceImpact())) {
            previewVo.setPriceImpactDesc(Utils.space(DictUtils.getValue(Constants.DictType.SPEC_REL_CONFIRM, tdDetail.getPriceImpact()))); // 价格影响确认
        }

        if (StringUtil.isNotBlank(tdDetail.getRoyaltyPayment())) {
            previewVo.setRoyaltyPaymentDesc(Utils.space(DictUtils.getValue(Constants.DictType.SPEC_REL_CONFIRM, tdDetail.getRoyaltyPayment()))); // 与货物有关的特许权使用费支付确认
        }

        previewVo.setBusinessMatter(Utils.space(tdDetail.getBusinessMatter()));
        previewVo.setBusinessMatter(Utils.space(previewVo.getBusinessMatterDesc())); // 业务类型-含有自报自缴类型

        //随附单据
        QueryVo qv = new QueryVo();
        qv.setId1(taskId);
        List<ToLicense> toLicenses = this.listService.listCertificate(qv);
        if (Utils.isNotEmpty(toLicenses)) {
            int i = 1;
            for (ToLicense vo : toLicenses) {
                if (StringUtil.isNotBlank(previewVo.getDocumentInfo())) {
                    previewVo.setDocumentInfo(previewVo.getDocumentInfo() + "，随附单证" + i + ":" + Utils.white(vo.getDocTypeDesc()) + Utils.white(vo.getDocNo()));
                } else {
                    previewVo.setDocumentInfo("随附单证" + i + ":" + Utils.white(vo.getDocTypeDesc()) + Utils.white(vo.getDocNo()));
                }
                i++;
            }
        }
        if (StringUtil.isNotBlank(tdDetail.getComment())) {
            previewVo.setMarkNo("备注：" + tdDetail.getComment()); // 备注
        }
        if (StringUtil.isNotBlank(tdDetail.getMarkNo())) {
            previewVo.setComment("  " + tdDetail.getMarkNo()); // 标记唛码
        }

        if (StringUtil.isNotBlank(tdDetail.getRelRecord())) {
            previewVo.setComment(Utils.white(previewVo.getComment()) + "  关联备案：" + tdDetail.getRelRecord()); // 标记唛码 + 关联备案
        }

        if (tdDetail.getQuantity() != null) {
            previewVo.setTotalPackageNumber(Utils.space(Utils.format(tdDetail.getQuantity(), this.df))); // 件数
        }

        if (tdDetail.getContainerNumber() != null) {
            previewVo.setContainerNumber(Utils.space("  集装箱数及号码：" + Utils.toString(tdDetail.getContainerNumber()))); // 集装箱数
        }


        if (StringUtil.isNotBlank(tdDetail.getDeclarationCustoms())) {
            previewVo.setDeclarationCustoms(Utils.space(tdDetail.getDeclarationCustoms())); // 申报地海关
            TpCustoms customs = this.tpCustomsDao.findByCode(tdDetail.getDeclarationCustoms());
            previewVo.setDeclarationCustomsDesc(Utils.space(customs.getName())); // 申报地海关名称
        }
        Date declare = transportation.getDeclareDate();
        if (transportation.getDeclareDate() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); // Date指定格式：yyyy-MM-dd
            previewVo.setDeclareDate(Utils.space(simpleDateFormat.format(declare))); // 申报日期
        }

        previewVo.setEntryId(Utils.space(tdHead.getEntryId())); // 海关编号
        previewVo.setGoodsLocation(Utils.space(transportation.getGoodsLocation())); // 货物存放地点
        previewVo.setTradeMode(Utils.space(transportation.getTradeMode())); // 监管方式
        if (StringUtil.isNotBlank(transportation.getTradeMode())) {
            previewVo.setTradeModeDesc(Utils.space(DictUtils.getValue(Constants.DictType.P_SUPERVISION_MODE, transportation.getTradeMode()))); // 监管方式名称
        }

        previewVo.setPod(Utils.space(transportation.getPod())); // 启运港
        if (StringUtil.isNotBlank(transportation.getPod())) {
            TpPort port = this.portDao.findByCode(transportation.getPod());
            previewVo.setPodDesc(Utils.space(port.getName())); // 启运港名称
        }
        previewVo.setTradeNation(Utils.space(transportation.getTradeNation())); // 贸易国
        previewVo.setTradeCountry(Utils.space(transportation.getTradeCountry())); // 启运国

        if (StringUtil.isNotBlank(transportation.getTradeNation())) {
            TpCountryRegin countryRegin = this.tpCountryReginDao.findByCode(transportation.getTradeNation());
            previewVo.setTradeNationDesc(Utils.space(countryRegin.getName())); // 贸易国名称
        }

        if (StringUtil.isNotBlank(transportation.getTradeCountry())) {
            TpCountryRegin countryRegin = this.tpCountryReginDao.findByCode(transportation.getTradeCountry());
            previewVo.setTradeCountryDesc(Utils.space(countryRegin.getName())); // 启运国名称
        }

        previewVo.setPoa(Utils.space(transportation.getPoa())); // 经停港
        if (StringUtil.isNotBlank(transportation.getPoa())) {
            TpPort port = this.portDao.findByCode(transportation.getPoa());
            previewVo.setPoaDesc(Utils.space(port.getName())); // 经停港名称
        }


        previewVo.setArrivalLocation(Utils.space(transportation.getArrivalLocation())); // 入境口岸
        if (StringUtil.isNotBlank(transportation.getArrivalLocation())) {
            TpDomesticPort domesticPort = this.tpDomesticPortDao.findByCode(transportation.getArrivalLocation());
            previewVo.setArrivalLocationDesc(Utils.space(domesticPort.getName())); // 入境口岸名称
        }


        previewVo.setTotalGrossweight(Utils.space(Utils.format(transportation.getTotalGrossweight(), this.df))); // 毛重
        previewVo.setTotalNetweight(Utils.space(Utils.format(transportation.getTotalNetweight(), this.df))); // 净重
        previewVo.setTransMode(Utils.space(transportation.getTransMode())); // 成交方式
        if (StringUtil.isNotBlank(transportation.getTransMode())) {
            previewVo.setTransModeDesc(Utils.space(DictUtils.getValue(Constants.DictType.P_TRANS_MODE, transportation.getTransMode()).replaceAll("&", "&amp;"))); // 成交方式名称
        }


        // TYPE = 'D' -- D表示境内收发货人
        // TYPE = 'E' -- E表示境外收发货人
        // TYPE = 'I' -- I表示消费使用单位
        // TYPE = 'R' -- R表示申报单位
        TdPartner tdPartner = this.partnerDao.findByHeadIdAndType(id, "D");
        if (tdPartner != null) {
            previewVo.setSocialCreditCode1(Utils.space(tdPartner.getSocialCreditCode())); // 境内收发货人-社会信用代码
            previewVo.setPartnerName1(Utils.space(WordUtils.reword(tdPartner.getPartnerName()))); // 境内收发货人-名称
        }


        tdPartner = this.partnerDao.findByHeadIdAndType(id, "E");
        if (tdPartner != null) {
            previewVo.setSocialCreditCode2(Utils.space(tdPartner.getSocialCreditCode())); // 境外收发货人-社会信用代码
            previewVo.setPartnerName2(Utils.space(WordUtils.reword(tdPartner.getPartnerName()))); // 境外收发货人-名称
        }


        tdPartner = this.partnerDao.findByHeadIdAndType(id, "I");
        if (tdPartner != null) {
            previewVo.setSocialCreditCode3(Utils.space(tdPartner.getSocialCreditCode())); // 消费使用单位-社会信用代码
            previewVo.setPartnerName3(Utils.space(WordUtils.reword(tdPartner.getPartnerName()))); // 消费使用单位-名称
        }


        tdPartner = this.partnerDao.findByHeadIdAndType(id, "R");
        if (tdPartner != null) {
            previewVo.setSocialCreditCode4(Utils.space(tdPartner.getSocialCreditCode())); // 申报单位-社会信用代码
            previewVo.setPartnerName4(Utils.space(WordUtils.reword(tdPartner.getPartnerName()))); // 申报单位-名称
        }


        String freightMarkDesc = null;
        String freightValue = null;
        String freightCurrencyDesc = null;

        String premiumMarkDesc = null;
        String premiumValue = null;
        String premiumCurrencyDesc = null;

        String incidentalMarkDesc = null;
        String incidentalValue = null;
        String incidentalCurrencyDesc = null;
        if (tdFee != null) {
            freightMarkDesc = tdFee.getFreightMark();
            freightValue = Utils.format(tdFee.getFreightValue(), this.df);
            freightCurrencyDesc = tdFee.getFreightCurrency();

            premiumMarkDesc = tdFee.getPremiumMark();
            premiumValue = Utils.format(tdFee.getPremiumValue(), this.df);
            premiumCurrencyDesc = tdFee.getPremiumCurrency();

            incidentalMarkDesc = tdFee.getIncidentalMark();
            incidentalValue = Utils.format(tdFee.getIncidentalValue(), this.df);
            incidentalCurrencyDesc = tdFee.getIncidentalCurrency();
        }
        if (StringUtil.isNotBlank(freightMarkDesc)) {
            freightMarkDesc = DictUtils.getValue(Constants.DictType.FEE_MARK, freightMarkDesc);
        }
        previewVo.setFreightMarkDesc(Utils.space(tdFee.getFreightMark())); // 运费标识

        previewVo.setFreightValue(Utils.space(freightValue)); // 运费率/价
        if (StringUtil.isNotBlank(freightCurrencyDesc)) {
            freightCurrencyDesc = DictUtils.getValue(Constants.DictType.P_CURRENCY_CODE, freightCurrencyDesc);
        }
        previewVo.setFreightCurrencyDesc(Utils.space(freightCurrencyDesc)); // 运费币种

        if (StringUtil.isNotBlank(premiumMarkDesc)) {
            premiumMarkDesc = DictUtils.getValue(Constants.DictType.FEE_MARK, premiumMarkDesc);
        }
        previewVo.setPremiumMarkDesc(Utils.space(tdFee.getPremiumMark())); // 保费标识
        previewVo.setPremiumValue(Utils.space(premiumValue));  // 保费率/价
        if (StringUtil.isNotBlank(premiumCurrencyDesc)) {
            premiumCurrencyDesc = DictUtils.getValue(Constants.DictType.P_CURRENCY_CODE, premiumCurrencyDesc);
        }
        previewVo.setPremiumCurrencyDesc(Utils.space(premiumCurrencyDesc)); // 保费币种

        if (StringUtil.isNotBlank(incidentalMarkDesc)) {
            incidentalMarkDesc = DictUtils.getValue(Constants.DictType.FEE_MARK, incidentalMarkDesc);
        }
        previewVo.setIncidentalMarkDesc(Utils.space(tdFee.getIncidentalMark())); // 杂费标识
        previewVo.setIncidentalValue(Utils.space(incidentalValue)); // 杂费率/价
        if (StringUtil.isNotBlank(incidentalCurrencyDesc)) {
            incidentalCurrencyDesc = DictUtils.getValue(Constants.DictType.P_CURRENCY_CODE, incidentalCurrencyDesc);
        }
        previewVo.setIncidentalCurrencyDesc(Utils.space(incidentalCurrencyDesc)); // 杂费币种
        previewVo.setName(Utils.space(null)); // 申报人员
        previewVo.setUserNo(Utils.space(null)); // 申报人员编号
        previewVo.setTel(Utils.space(null)); // 电话
        previewVo.setAnnotations(Utils.space(null)); // 海关批注

        /**
         * <p>Field list: 表格list</p>
         */
        List<TdHsItem> tdHsItems = this.hsItemDao.findByHeadIdOrderByItemNoAsc(id);
        // 如果商品明细没有达到6条数据，则添加空数据用以补充表格行
        TdHsItem hsItem = new TdHsItem();
        for (int i = tdHsItems.size(); i < 6; i++) {
            if (tdHsItems.size() > 0) {
                hsItem.setId(tdHsItems.get(0).getId());
            } else {
                hsItem.setId(Long.parseLong(i + ""));
            }
            tdHsItems.add(hsItem);
        }
        if (Utils.isNotEmpty(tdHsItems)) {
            for (TdHsItem tdHsItem : tdHsItems) {
                ItemPreviewVo previewVos = new ItemPreviewVo();
                TdHsItemSupplement tdHsItemSupplement = null;
                if (tdHsItem.getId() != null && tdHsItem.getHeadId() != null) {
                    tdHsItemSupplement = this.hsItemSupplementDao.findByItemId(tdHsItem.getId());
                }
                if (tdHsItemSupplement != null) {
                    Integer itemNo = tdHsItem.getItemNo();
                    if (null != itemNo) {
                        previewVos.setItemNo(Utils.space(itemNo.toString())); // 项号
                    } else {
                        previewVos.setItemNo(Utils.space(null));
                    }
                    if (null != tdHsItem.getHsCode()) {
                        previewVos.setHsCode(Utils.space(tdHsItem.getHsCode())); // 商品编号
                    } else {
                        previewVos.setHsCode(Utils.space(null));
                    }
                    if (null != tdHsItem.getHsName()) {
                        previewVos.setHsName(Utils.space(tdHsItem.getHsName())); // 商品名称
                    } else {
                        previewVos.setHsName(Utils.space(null));
                    }
//                    previewVos.setItemNo(Utils.space(itemNo.toString())); // 项号
//                    previewVos.setHsCode(Utils.space(tdHsItem.getHsCode())); // 商品编号
//                    previewVos.setHsName(Utils.space(tdHsItem.getHsName())); // 商品名称
                    List<String> illegalCharacter = new ArrayList<>(); //xml非法字符
                    illegalCharacter.add("&");
                    illegalCharacter.add("'");
                    illegalCharacter.add("\"");
                    illegalCharacter.add(">");
                    illegalCharacter.add("<");
                    if (null != tdHsItem.getId()) {
                        previewVos.setItemSpecModel(Utils.space(Utils.removeSplitter(this.itemAttributeDao.getAttributeByItemId(tdHsItem.getId())))); // 规格型号
                    } else {
                        previewVos.setItemSpecModel(Utils.space(null));
                    }
//                    previewVos.setItemSpecModel(Utils.space(this.itemAttributeDao.getAttributeByItemId(tdHsItem.getId()))); // 规格型号
                    // 非法字符串替换
                    for (String word : illegalCharacter) {
                        if (StringUtil.isNotBlank(previewVos.getItemSpecModel())) {
                            if (previewVos.getItemSpecModel().contains(word)) {
                                previewVos.setItemSpecModel(WordUtils.reword(previewVos.getItemSpecModel()));
                            } else {
                                previewVos.setItemSpecModel(Utils.white(previewVos.getItemSpecModel()));
                            }
                        } else {
                            previewVos.setItemSpecModel(Utils.white(previewVos.getItemSpecModel()));
                        }
                    }
                    previewVos.setUnitQty(Utils.space(Utils.format(tdHsItem.getQuantity(), this.df))); // 计量单位数量1
                    String unitDesc = null;
                    if (StringUtil.isNotBlank(tdHsItem.getDeclareUnit())) {
                        unitDesc = DictUtils.getValue(Constants.DictType.P_MEASUREMENT_UNIT, tdHsItem.getDeclareUnit());
                    }
                    previewVos.setUnitDesc(Utils.space(unitDesc)); // 成交单位名称
                    previewVos.setUnitPrice(Utils.space(Utils.format(tdHsItem.getUnitPrice(), this.df))); // 单价
                    previewVos.setCurrencyAmount(Utils.space(Utils.format(tdHsItem.getCurrencyAmount(), this.df))); // 总价
                    String currencyCodeDesc = null;
                    if (StringUtil.isNotBlank(tdHsItem.getCurrencyCode())) {
                        currencyCodeDesc = DictUtils.getValue(Constants.DictType.P_CURRENCY_CODE, tdHsItem.getCurrencyCode());
                    }
                    previewVos.setCurrencyCodeDesc(Utils.space(currencyCodeDesc)); // 币制
                    String originCountry = null;
                    if (StringUtil.isNotBlank(tdHsItem.getOriginCountry())) {
                        originCountry = this.tpCountryReginDao.findByCode(tdHsItem.getOriginCountry()).getName();
                    }
                    previewVos.setOriginCountry(Utils.space(originCountry)); // 原产国
                    String destinationCountry = null;
                    if (StringUtil.isNotBlank(tdHsItem.getDestinationCountry())) {
                        destinationCountry = this.tpCountryReginDao.findByCode(tdHsItem.getDestinationCountry()).getName();
                    }
                    previewVos.setDestinationCountry(Utils.space(destinationCountry)); // 最终目的国
                    String domesticDestCodeDesc = null;
                    if (StringUtil.isNotBlank(tdHsItemSupplement.getDomesticDestCode())) {
                        TpDomesticArea tpDomesticArea = this.tpDomesticAreaDao.findByCode(tdHsItemSupplement.getDomesticDestCode());
                        if (StringUtil.isNotBlank(tdHsItemSupplement.getDestCode())) {
                            TpAdministrativeDivision dest = this.tpAdministrativeDivisionDao.findByCode(tdHsItemSupplement.getDestCode());
                            domesticDestCodeDesc = "(" + tdHsItemSupplement.getDomesticDestCode() + "/" + tdHsItemSupplement.getDestCode() + ")" + tpDomesticArea.getName() + "/" + dest.getName();
                        } else {
                            domesticDestCodeDesc = "(" + tdHsItemSupplement.getDomesticDestCode() + ")" + tpDomesticArea.getName();
                        }
                    } else {
                        if (StringUtil.isNotBlank(tdHsItemSupplement.getDestCode())) {
                            TpAdministrativeDivision dest = this.tpAdministrativeDivisionDao.findByCode(tdHsItemSupplement.getDestCode());
                            domesticDestCodeDesc = "(" + tdHsItemSupplement.getDestCode() + ")" + dest.getName();
                        }
                    }
                    previewVos.setDomesticDestCodeDesc(Utils.space(domesticDestCodeDesc)); // 境内目的地
                    String taxFreeMethodDesc = null;
                    if (StringUtil.isNotBlank(tdHsItem.getTaxFreeMethod())) {
                        taxFreeMethodDesc = DictUtils.getValue(Constants.DictType.P_TAX_REDUCTION_EXEMPTION, tdHsItem.getTaxFreeMethod());
                    }
                    previewVos.setTaxFreeMethodDesc(Utils.space(taxFreeMethodDesc)); // 征免方式

                } else {
                    previewVos.setItemNo(Utils.space(null));
                    previewVos.setHsCode(Utils.space(null));
                    previewVos.setHsName(Utils.space(null));
                    previewVos.setItemSpecModel(Utils.space(null));
                    previewVos.setUnitQty(Utils.space(null));
                    previewVos.setUnitDesc(Utils.space(null));
                    previewVos.setUnitPrice(Utils.space(null));
                    previewVos.setCurrencyAmount(Utils.space(null));
                    previewVos.setCurrencyCodeDesc(Utils.space(null));
                    previewVos.setOriginCountry(Utils.space(null));
                    previewVos.setDepartureCountry(Utils.space(null));
                    previewVos.setDestinationCountry(Utils.space(null));
                    previewVos.setDomesticDestCodeDesc(Utils.space(null));
                    previewVos.setTaxFreeMethodDesc(Utils.space(null));
                }
                previewVo.add(previewVos);
            }
        }

        String businessType = tdHead.getBusinessType();
        String type = null;
        if ("I".equals(businessType)) {
            type = "Y-IBGD";
        }
        if ("E".equals(businessType)) {
            type = "Y-EBGD";
        }
        if ("RI".equals(businessType)) {
            type = "Y-RIBAQD";
        }
        if ("RE".equals(businessType)) {
            type = "Y-REBAQD";
        }
        TmDocumentTemplate temp = this.getDocumentTemplate(tdHead.getDeptId(), type);
        String pdf = null;
        if (temp != null) {
            String name = FileUtil.getName(temp.getXmlPath());
            String docNo = DateUtils.fmtDate8(new Date()) + Utils.randomNumber(6);
            // 生成PDF
            String filename = type + "-" + DateUtils.fmtDate8(new Date()) + Utils.randomNumber(6);
            pdf = WordUtils.parsej(name, filename, previewVo, temp.getZipPath());
            filename = filename + ".pdf";
            /* 文档覆盖生成 */
            List<ToDocument> toDocument = this.toDocumentDao.findByTaskId(taskId);
            if (toDocument.size() > 0) {
                this.toDocumentDao.deleteByTaskIdAndDocType(taskId, type);
            }
            // 创建单据记录
            DocumentUtils.addDocument(filename, FileUtil.getPath(pdf), null, tdHead.getTaskId(), docNo, type, false);

        }
        return type;
    }

    @Override
    @Transactional
    public String paperlessCheckEmail(Long id) {
        TdHead head = this.headDao.get(id);
        if (null != head) {
            TdDeclarationTask task = this.declarationTaskDao.get(head.getTaskId());
            Date now = new Date();
            LoginUser user = UserUtils.getLoginUser();
            head.setSideStatus(Constants.IMP_STATUS_PAPERLESS);
            head.setUpdateBy(user.getUserNo());
            head.setUpdateTime(now);
            this.headDao.save(head);

            TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
            status.setTaskId(head.getTaskId());
            status.setOperation(Constants.Dtask.OPER_PAPERLESS);
            status.setStatus(task.getStatus());
            status.setIsRead(Constants.NO);
            status.init(user, now);
            DeclUtils.setTaskStatusCompany(status, user);
            this.declarationTaskStatusDao.save(status);

            this.paperlessEmail(head, task, now, user.getName());
        }
        return null;
    }

    @Override
    public List<String> previewPdf(long id, Long taskId) throws Exception {
        TdHead tdHead = this.headDao.findByIdAndTaskId(id, taskId);
        String businessType = tdHead.getBusinessType();
        TdDeclarationTask task = this.declarationTaskDao.get(taskId);
        if (task.getBusinessType().equals(Constants.BusinessType.CHECKLIST_IMPORT) || task.getBusinessType().equals(Constants.BusinessType.CHECKLIST_EXPORT)
                || task.getBusinessType().equals(Constants.BusinessType.CHECKLIST_FTA_IMPORT) || task.getBusinessType().equals(Constants.BusinessType.CHECKLIST_FTA_EXPORT)) {
            businessType = task.getDeclType();
        }
        String type = null;
        if ("I".equals(businessType)) {
            type = "Y-IBGD";
        }
        if ("E".equals(businessType)) {
            type = "Y-EBGD";
        }
        if ("RI".equals(businessType)) {
            type = "Y-RIBAQD";
        }
        if ("RE".equals(businessType)) {
            type = "Y-REBAQD";
        }
        ToDocument toDocument = this.toDocumentDao.findFirstByTaskIdAndDocTypeOrderByCreateTimeDesc(taskId, type);
        List<String> list = new ArrayList<>();
        if (toDocument == null) {
            type = this.previewsPdf(id, taskId);
            toDocument = this.toDocumentDao.findFirstByTaskIdAndDocTypeOrderByCreateTimeDesc(taskId, type);
            list.add(Utils.toString(toDocument.getId()));
            list.add(toDocument.getDocFileName());
        } else {
            list.add(Utils.toString(toDocument.getId()));
            list.add(toDocument.getDocFileName());
        }
        return list;
    }

    private void paperlessEmail(TdHead head, TdDeclarationTask task, Date now, String name) {
        if (!DeclUtils.email(task.getSourceBy())) {
            return;
        }

        Set<Long> ids = Utils.toSet(this.headDao.queryPaperlessUser(head.getId(), Constants.User.TYPE_FINANCIAL));
        ids.add(UserUtils.getId(head.getCreateBy()));
        if (Utils.isNotEmpty(ids)) {
            ids = UserUtils.filter(ids, Constants.User.PARA_PAPERLESS_EMAIL);
        }
        List<UserEmailVo> users = this.userDao.findByIdIn(ids);
        List<UserEmailVo> list = new ArrayList<>();
        // 手动新建的任务，显示任务对应的补充凭证号1和2
        String pdocNo = Utils.link(Constants.SLASH_WITH_SPACE, task.getPdocNo1(), task.getPdocNo2(), task.getPdocNo3());
        String titlePdocNo = pdocNo;
        // 与GTS凭证号关联的的任务在邮件内显示内部凭证号类型及对应号码
        String sourceBy = null;
        if (task.getSourceBy().equals(Constants.Dtask.SOURCE_BY_GTS)) {
            pdocNo = ListUtil.getPdocNo(task.getGtsInternalNo());
            sourceBy = Constants.Dtask.SOURCE_BY_GTS;
        }
        if (StringUtil.isNotBlank(titlePdocNo)) {
            titlePdocNo = "凭证:" + titlePdocNo + "; ";
        }
        if (Utils.isNotEmpty(users)) {
            for (UserEmailVo vo : users) {
                if (EmailUtil.isMulti(vo.getEmail())) {
                    List<String> temp = EmailUtil.getEmail(vo.getEmail());
                    for (String e : temp) {
                        list.add(new UserEmailVo(vo.getId(), vo.getName(), e, vo.getLanguage()));
                    }
                } else {
                    list.add(vo);
                }
            }
            List<TnEmailInfo> infos = new ArrayList<>();
            if ("s12".equals(task.getTradeMode()) || "s15".equals(task.getTradeMode()) || "s16".equals(task.getTradeMode())) { //客户自提发送所有邮件通知用户于一封邮件内(不通知其他联系人)
                DeclTaskUserEmailVo email = this.declTaskEmailService.getAllDeclTaskEmailList(task, Constants.User.PARA_PAPERLESS_EMAIL, true);
                String trademodel = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
                if (StringUtil.isNotBlank(trademodel)) {
                    trademodel = trademodel.substring(trademodel.length() - 4);
                }
                if (Utils.isNotEmpty(email.getAll())) {
                    String em = "";
                    for (UserEmailVo vo : email.getAll()) {
                        if (StringUtil.isNotBlank(vo.getEmail())) {
                            if (vo.getId() != null) {
                                if ("".equals(em)) {
                                    em = vo.getEmail();
                                } else {
                                    em = em + ";" + vo.getEmail();
                                }
                            }
                        }
                    }
                    TnEmailInfo info = new TnEmailInfo();
                    info.setRecipient(em);
                    info.setLang("zh");
                    info.setTitle(Utils.white(trademodel) + "," + titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 通关无纸化审结");
                    info.setTemplate("picked_up_decl_paperless.zh");
                    info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                    info.put("taskNo", task.getTaskNo());
                    info.put("taskUrl", DeclUtils.getTaskUrl(task));
                    info.put("serialNumber", Utils.white(task.getDeclaranceSerialNumber()));
                    info.put("declUrl", DeclUtils.getDeclUrl(task));
                    info.put("entryId", Utils.white(head.getEntryId()));
                    info.put("operator", Utils.white(name));
                    info.put("createTime", DateUtils.fmtLongDate(now));
                    info.put("pdocNo", pdocNo);
                    info.put("sourceBy", sourceBy);
                    infos.add(info);
                }
            } else {
                if (Utils.isNotEmpty(list)) {
                    // 任务转派用户
                    Set<Long> transUser = Utils.toSet(this.transferUserDao.getUserIdListByTaskId(task.getId()));
                    List<TsUserInfo> userInfos = this.userDao.findAllByIdIn(transUser);
                    List<TsUserInfo> mail = new ArrayList<>();
                    Boolean isSolvy = false;
                    if (Utils.isNotEmpty(userInfos)) {
                        for (TsUserInfo info : userInfos) {
                            if ("U".equals(info.getType())) {
                                mail.add(info);
                                isSolvy = true;
                            }
                        }
                        if (isSolvy) {
                            // 转派其他用户
                            List<String> transEmails = this.transferDao.findEmailByTaskId(task.getId());
                            for (int i = 0; i < transEmails.size(); i++) {
                                TsUserInfo vo = new TsUserInfo();
                                vo.setName(transEmails.get(i));
                                vo.setEmail(transEmails.get(i));
                                mail.add(vo);
                            }
                        }
                    }
                    if (Utils.isNotEmpty(mail)) {
                        for (TsUserInfo vo : mail) {
                            UserEmailVo emailVo = new UserEmailVo(vo.getId(), vo.getName(), vo.getEmail(), vo.getLanguage());
                            list.add(emailVo);
                        }
                    }
                    String title = titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 通关无纸化审结";
                    String template = "decl_paperless.zh";

                    TnEmailInfo info = null;
                    for (UserEmailVo vo : list) {
                        if (EmailUtil.validateEmail(vo.getEmail())) {
                            info = new TnEmailInfo();
                            info.setRecipient(vo.getEmail());
                            info.setLang(vo.getLanguage());
                            info.setTitle(title);
                            info.setTemplate(template);
                            info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                            info.put("name", vo.getName());
                            info.put("taskNo", task.getTaskNo());
                            info.put("taskUrl", DeclUtils.getTaskUrl(task));
                            info.put("serialNumber", Utils.white(task.getDeclaranceSerialNumber()));
                            info.put("declUrl", DeclUtils.getDeclUrl(task));
                            info.put("entryId", Utils.white(head.getEntryId()));
                            info.put("operator", Utils.white(name));
                            info.put("createTime", DateUtils.fmtLongDate(now));
                            info.put("pdocNo", pdocNo);
                            info.put("sourceBy", sourceBy);
                            infos.add(info);
                        }
                    }
                }
            }
            EmailStoreMq.putAll(infos);
        }
    }

    @Override
    public ImpStatusVo getDeclStatus(Long id) {
        return this.headDao.findByIdOrderByStatus(id);
    }

    @Override
    public String isExistEntryId(Long id, String entryId) {
        if (StringUtil.isNotBlank(entryId)) {
            // 校验此报关单号在系统内是否已存在
            int result = this.headDao.isExistEntryId(id, entryId);
            if (result > 0) {
                return "该报关单号在系统内已存在，是否继续提交报关单号？";
            }
        }
        return null;
    }

    @Override
    public List<TdHsItem> listPrice(QueryVo qv) {
        Paras pa = new Paras();
        if (null != qv.getPid()) {
            pa.add("headId", qv.getPid());
        }
        qv.orderBy("itemNo", SortOrder.ASC);
        return this.hsItemDao.findListBy(pa, qv);
    }

    @Override
    public void savePrice(HsVo vo, LoginUser user) {
        Date now = new Date();
        List<TdHsItem> items = vo.getItems();
        List<TdHsItem> list = new ArrayList<>();
        for (TdHsItem item : items) {
            TdHsItem tdHsItem = this.hsItemDao.get(item.getId());
            tdHsItem.setPartBatchPrice(item.getPartBatchPrice());
            if (Utils.isNotNull(item.getPartHistoryPrice())) {
                tdHsItem.setPartHistoryPrice(item.getPartHistoryPrice());
            }
            if (Utils.isNotNull(item.getPartHistorySalePrice())) {
                tdHsItem.setPartHistorySalePrice(item.getPartHistorySalePrice());
            }
            tdHsItem.update(user, now);
            list.add(tdHsItem);
        }
        this.hsItemDao.saveAll(list);
        TdDeclarationTask task = this.declarationTaskDao.findByDeclId(items.get(0).getHeadId());
        task.setPriceCheckStatus(Constants.Dtask.STATUS_PRICE_CHECK);
        task.setPriceCheckMark(Constants.Dtask.APPROVAL_MARK_NORMAL);
        task.setUpdateBy(user.getUserNo());
        task.setUpdateTime(now);
        this.declarationTaskDao.save(task);
        TdDeclarationTaskStatus create = DeclUtils.createStatus(task, Constants.Dtask.OPER_CREATE_PRICE, null, null, user, now);
        this.declarationTaskStatusDao.save(create);
    }

    @Override
    public List<?> priceList(QueryVo qv) {
        TdDeclarationTask task = this.declarationTaskDao.get(qv.getId());
        Paras pa = new Paras();

        String so = DictUtils.getValue(Constants.DictType.GTS_INTERNAL_TYPE, "114");
        String dn = DictUtils.getValue(Constants.DictType.GTS_INTERNAL_TYPE, "73");
        if (null != task.getBusinessType() && Constants.BusinessType.CHECKLIST_FTA_EXPORT.equals(task.getBusinessType())) {
            if (null == task.getListId()) {
                return null;
            }
            pa.add("headId", task.getListId());
            // qv.orderBy("declareItemNo", SortOrder.ASC);
            List<ToItem> listBy = this.toItemDao.findListBy(pa, qv);
            for (ToItem vo : listBy) {
                String docrefId = null;
                String docrefId2 = null;
                if ("114".equals(vo.getDocRefType()) || so.equals(vo.getDocRefType())) { // SO
                    docrefId = vo.getDocRefId();
                } else if ("114".equals(vo.getDocRefType2()) || so.equals(vo.getDocRefType2())) { // SO
                    docrefId = vo.getDocRefId2();
                }
                vo.setDocRefId(docrefId); // SO
                if (StringUtil.isBlank(vo.getDeclareItemNo())) {
                    vo.setDeclareItemNo("0");
                }
            }
            Collections.sort(listBy, new Comparator<ToItem>() {
                @Override
                public int compare(ToItem h1, ToItem h2) {
                    return Utils.parseInt(h1.getDeclareItemNo()) - Utils.parseInt(h2.getDeclareItemNo());
                }
            });
            return listBy;
        } else {
            if (null == task.getDeclId()) {
                return null;
            }
            pa.add("headId", task.getDeclId());
            qv.orderBy("itemNo", SortOrder.ASC);
            List<TdHsItem> listBy = this.hsItemDao.findListBy(pa, qv);
            for (TdHsItem vo : listBy) {
                String docrefId = null;
                String docrefId2 = null;
                if ("114".equals(vo.getDocRefType()) || so.equals(vo.getDocRefType())) { // SO
                    docrefId = vo.getDocRefId();
                } else if ("114".equals(vo.getDocRefType2()) || so.equals(vo.getDocRefType2())) { // SO
                    docrefId = vo.getDocRefId2();
                }
                vo.setDocRefId(docrefId); // SO
            }
            return listBy;
        }
    }

    @Override
    public void priceRecheck(Long taskId, String checkType, String checkRemarkText, LoginUser user) {
        Date now = new Date();
        TdDeclarationTask task = this.declarationTaskDao.get(taskId);
        TdDeclarationTaskStatus create = DeclUtils.createStatus(task, Constants.Dtask.OPER_CREATE_PRICE, null, null, user, now);

        if (Constants.YES.equals(checkType)) {
            // 通过
            task.setPriceCheckStatus(Constants.Dtask.STATUS_PRICE_ACCEPT);
            task.setPriceCheckMark(Constants.Dtask.APPROVAL_MARK_PASS);
            create.setOperation(Constants.Dtask.OPER_ACCEPT_PRICE);
            create.setMsg(checkRemarkText);
            // 替换审核后价格
            if (null != task.getListId()) { // 主表商品明细
                List<ToItem> toItems = this.toItemDao.findByHeadId(task.getListId());
                for (ToItem item : toItems) {
                    item.setRepPrice(item.getUnitPrice()); // 原单价
                    if (Constants.YES.equals(item.getRepMark())) {
                        // item.setRepPrice(item.getUnitPrice()); // 原单价
                        item.setUnitPrice(item.getPartBatchPrice()); // 物料批次进价替换原单价
                        item.setCurrencyAmount(item.getPartBatchPrice().multiply(new BigDecimal(item.getQuantity())));
                    }

                }
                this.toItemDao.saveAll(toItems);
            } else {
                if (null != task.getDeclId()) {
                    List<TdHsItem> hsItems = this.hsItemDao.findByHeadId(task.getDeclId());
                    for (TdHsItem item : hsItems) {
                        item.setRepPrice(item.getUnitPrice()); // 原单价
                        if (Constants.YES.equals(item.getRepMark())) {
                            item.setUnitPrice(item.getPartBatchPrice()); // 物料批次进价替换原单价
                            item.setCurrencyAmount(item.getPartBatchPrice().multiply(item.getQuantity()));
                        }
                    }
                    this.hsItemDao.saveAll(hsItems);
                }
            }
            // 如果贸易方式为s12-国内外币销售-客户保税时任务状态待为上传客户核注清单
            if ("s15".equals(task.getTradeMode()) && Constants.BusinessType.CHECKLIST_FTA_EXPORT.equals(task.getBusinessType())) {
                task.setStatus(Constants.Dtask.STATUS_CHECKLIST_CLIENT_UPLOAD_PENDING);
            }
        } else if (Constants.NO.equals(checkType)) {
            // 未通过
            task.setPriceCheckStatus(Constants.Dtask.STATUS_PRICE_REJECT);
            create.setOperation(Constants.Dtask.OPER_REJECT_PRICE);
            create.setMsg(checkRemarkText);

        }
        DeclUtils.setTaskStatusCompany(create, user);
        this.declarationTaskStatusDao.save(create);

        task.setUpdateBy(user.getUserNo());
        task.setUpdateTime(now);
        this.declarationTaskDao.save(task);
        if (Constants.YES.equals(checkType)) {
            //审核通过邮件
            this.sendEmailApprovedSuccess(taskId, user, checkRemarkText);
        } else {
            //审核失败邮件
            this.sendEmailApprovedFailed(taskId, user, checkRemarkText);
        }


    }


    /**
     * <p>Description: 价格审核通过(客户自提价格审核通过后，再次触发的核注清单派单邮件包含其他联系人)</p>
     *
     * @param taskId          任务id
     * @param user            当前用户
     * @param checkRemarkText 备注
     * @return
     */
    public void sendEmailApprovedSuccess(Long taskId, LoginUser user, String checkRemarkText) {
        Date now = new Date();
        /*价格审核发送邮件通知所有相关用户*/
        TdDeclarationTask task = this.declarationTaskDao.get(taskId);
        TsUserInfo userInfo = this.userDao.findByUserNo(task.getCreateBy());


        // 手动新建的任务，显示任务对应的补充凭证号1和2
        String pdocNo = Utils.white(task.getPdocNo1()) + " / " + Utils.white(task.getPdocNo2()) + " / " + Utils.white(task.getPdocNo3());
        String titlePdocNo = pdocNo;
        // 与GTS凭证号关联的的任务在邮件内显示内部凭证号类型及对应号码
        String sourceBy = null;
        if (task.getSourceBy().equals(Constants.Dtask.SOURCE_BY_GTS)) {
            pdocNo = ListUtil.getPdocNo(task.getGtsInternalNo());
            sourceBy = Constants.Dtask.SOURCE_BY_GTS;
        }
        if (StringUtil.isNotBlank(titlePdocNo)) {
            titlePdocNo = "凭证:" + titlePdocNo + "; ";
        }
        //获取任务附件
        String filename = null;
        String path = DocumentUtils.createZipFileWithTaskIdIsNoty(task.getId(), task.getTaskNo());

        if (StringUtil.isNotBlank(path)) {
            filename = task.getTaskNo() + ".zip";
        }
        DeclTaskUserEmailVo emails = this.declTaskEmailService.getAllDeclTaskEmailList(task, Constants.User.PARA_PRICE_AUDIT_EMAIL, true);

        //邮箱
        String tradeMode = this.dictDao.getValue2ByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
        if (Utils.isNotEmpty(emails.getAll())) {
            List<TnEmailInfo> infos = new ArrayList<>();

            if ("s12".equals(task.getTradeMode()) || "s15".equals(task.getTradeMode()) || "s16".equals(task.getTradeMode())) { //客户自提发送一封邮件
                String trademodel = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
                if (StringUtil.isNotBlank(trademodel)) {
                    trademodel = trademodel.substring(trademodel.length() - 4);
                }
                String em = "";
                String name = "";
                for (UserEmailVo vo : emails.getAll()) {
                    if (vo.getId() != null) {
                        if ("".equals(em)) {
                            em = vo.getEmail();
                        } else {
                            em = em + ";" + vo.getEmail();
                        }
                        if ("".equals(name)) {
                            name = vo.getName();
                        } else {
                            name = name + ";" + vo.getName();
                        }
                    }
                }
                TnEmailInfo info = new TnEmailInfo();
                info.setRecipient(em);
                info.setLang("zh");
                info.setAttachment(path);
                info.setAttachmentFilename(filename);
                info.setTitle(Utils.white(trademodel) + "," + titlePdocNo + "任务：" + task.getTaskNo() + " eCustoms:  价格审核通过");
                info.setTemplate("price_review_success.zh");
                info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                info.put("name", name);
                info.put("taskNo", task.getTaskNo());
                info.put("taskUrl", DeclUtils.getTaskUrl(task));
                info.put("serialNumber", task.getDeclaranceSerialNumber());
                info.put("declUrl", DeclUtils.getDeclUrl(task));
                info.put("createBy", user.getName());
                info.put("pdocNo", pdocNo);
                info.put("sourceBy", sourceBy);
                info.put("company", UserUtils.getBuName(user));
                info.put("createTime", DateUtils.fmtLongDate(now));
                info.put("tradeMode", tradeMode);
                info.put("comment", checkRemarkText);
                infos.add(info);

            } else {
                for (UserEmailVo vo : emails.getAll()) {
                    TnEmailInfo info = new TnEmailInfo();
                    info.setRecipient(vo.getEmail());
                    info.setLang("zh");
                    info.setAttachment(path);
                    info.setAttachmentFilename(filename);
                    info.setTitle(titlePdocNo + "任务：" + task.getTaskNo() + " eCustoms:  价格审核通过");
                    info.setTemplate("price_review_success.zh");
                    info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                    info.put("name", vo.getName());
                    info.put("taskNo", task.getTaskNo());
                    info.put("taskUrl", DeclUtils.getTaskUrl(task));
                    info.put("serialNumber", task.getDeclaranceSerialNumber());
                    info.put("declUrl", DeclUtils.getDeclUrl(task));
                    info.put("createBy", user.getName());
                    info.put("pdocNo", pdocNo);
                    info.put("sourceBy", sourceBy);
                    info.put("company", UserUtils.getBuName(user));
                    info.put("createTime", DateUtils.fmtLongDate(now));
                    info.put("tradeMode", tradeMode);
                    info.put("comment", checkRemarkText);
                    infos.add(info);
                }
            }

            EmailStoreMq.putAll(infos);
        }

        //客户自提价格审核通过后再次发送任务派单邮件，邮件包含其他联系人
        if ("s12".equals(task.getTradeMode()) || "s15".equals(task.getTradeMode()) || "s16".equals(task.getTradeMode())) {
            ToHead th = this.toHeadDao.get(task.getListId());
            String url = "/checklist/detail?list=" + th.getId();
            //自贸核注清单
            if (Constants.BusinessType.CHECKLIST_FTA_EXPORT.equals(th.getBusinessType()) || Constants.BusinessType.CHECKLIST_FTA_IMPORT.equals(th.getBusinessType())) {
                url = "/checklist/fta-detail?list=" + th.getId() + "&businessType=" + th.getBusinessType();
            }
            String trademodel = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
            if (StringUtil.isNotBlank(trademodel)) {
                trademodel = trademodel.substring(trademodel.length() - 4);
            }

            List<TnEmailInfo> infos = new ArrayList<>();
            if (Utils.isNotEmpty(emails.getAll())) {
                // 发送通知邮件

                String em = "";
                String name = "";
                for (UserEmailVo vo : emails.getAll()) {
                    if ("".equals(em)) {
                        em = vo.getEmail();
                    } else {
                        em = em + ";" + vo.getEmail();
                    }
                    if ("".equals(name)) {
                        name = vo.getName();
                    } else {
                        name = name + ";" + vo.getName();
                    }
                }
                TnEmailInfo info = new TnEmailInfo();
                info.setRecipient(em);
                info.setLang("zh");
                info.setAttachment(path);
                info.setAttachmentFilename(filename);
                info.setTitle(Utils.white(trademodel) + "," + titlePdocNo + "任务:" + task.getTaskNo() + " eCustoms: 任务分配成功 - 核注清单");
                info.setTemplate("picked_up_checklist_dispatch.zh");
                info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                info.put("name", name);
                info.put("assignorCompany", "Solvay");
                info.put("assignor", user.getName());
                info.put("taskNo", task.getTaskNo());
                info.put("taskUrl", DeclUtils.getTaskUrl(task));
                info.put("listNo", th.getInternalNumber());
                if (Constants.BusinessType.CHECKLIST_FTA_EXPORT.equals(th.getBusinessType()) || Constants.BusinessType.CHECKLIST_FTA_IMPORT.equals(th.getBusinessType())) {
                    info.put("declUrl", DeclUtils.getftaCheckListlUrl(th.getId(), th.getBusinessType()));
                } else {
                    info.put("declUrl", DeclUtils.getCheckListlUrl(th.getId()));
                }
                info.put("createTime", DateUtils.fmtLongDate(now));
                info.put("comment", Utils.white(task.getComment()));
                info.put("pdocNo", pdocNo);
                info.put("sourceBy", sourceBy);
                infos.add(info);
            }
            EmailStoreMq.putAll(infos);
        }
    }

    /**
     * <p>Description: 价格审核失败</p>
     *
     * @param taskId          任务id
     * @param user            当前用户
     * @param checkRemarkText 备注
     * @return
     */
    public void sendEmailApprovedFailed(Long taskId, LoginUser user, String checkRemarkText) {
        Date now = new Date();
        /*价格审核发送邮件通知所有相关用户*/
        TdDeclarationTask task = this.declarationTaskDao.get(taskId);
        TsUserInfo userInfo = this.userDao.findByUserNo(task.getCreateBy());
        // 手动新建的任务，显示任务对应的补充凭证号1和2
        String pdocNo = Utils.white(task.getPdocNo1()) + " / " + Utils.white(task.getPdocNo2()) + " / " + Utils.white(task.getPdocNo3());
        String titlePdocNo = pdocNo;
        // 与GTS凭证号关联的的任务在邮件内显示内部凭证号类型及对应号码
        String sourceBy = null;
        if (task.getSourceBy().equals(Constants.Dtask.SOURCE_BY_GTS)) {
            pdocNo = ListUtil.getPdocNo(task.getGtsInternalNo());
            sourceBy = Constants.Dtask.SOURCE_BY_GTS;
        }
        if (StringUtil.isNotBlank(titlePdocNo)) {
            titlePdocNo = "凭证:" + titlePdocNo + "; ";
        }

//        if (null != type) {
//            taskUsers = UserUtils.filter(taskUsers, type);
//        }


        DeclTaskUserEmailVo emails = this.declTaskEmailService.getAllDeclTaskEmailList(task, Constants.User.PARA_PRICE_AUDIT_EMAIL, true);

        //邮箱
        String tradeMode = this.dictDao.getValue2ByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
        if (Utils.isNotEmpty(emails.getAll())) {
            List<TnEmailInfo> infos = new ArrayList<>();
            if ("s12".equals(task.getTradeMode()) || "s15".equals(task.getTradeMode()) || "s16".equals(task.getTradeMode())) { //客户自提发送一封邮件,审核失败邮件通知不包含其他联系人
                String trademodel = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.TRADE_MODE_ALL, task.getTradeMode());
                if (StringUtil.isNotBlank(trademodel)) {
                    trademodel = trademodel.substring(trademodel.length() - 4);
                }
                String em = "";
                String name = "";
                for (UserEmailVo vo : emails.getAll()) {
                    if (vo.getId() != null) {
                        if ("".equals(em)) {
                            em = vo.getEmail();
                        } else {
                            em = em + ";" + vo.getEmail();
                        }
                        if ("".equals(name)) {
                            name = vo.getName();
                        } else {
                            name = name + ";" + vo.getName();
                        }
                    }
                }

                TnEmailInfo info = new TnEmailInfo();
                info.setRecipient(em);
                info.setLang("zh");
                info.setTitle(Utils.white(trademodel) + "," + titlePdocNo + "任务：" + task.getTaskNo() + " eCustoms:  价格审核失败");
                info.setTemplate("price_review_failed.zh");
                info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                info.put("name", name);
                info.put("taskNo", task.getTaskNo());
                info.put("taskUrl", DeclUtils.getTaskUrl(task));
                info.put("serialNumber", task.getDeclaranceSerialNumber());
                info.put("declUrl", DeclUtils.getDeclUrl(task));
                info.put("pdocNo", pdocNo);
                info.put("sourceBy", sourceBy);
                info.put("createBy", user.getName());
                info.put("company", UserUtils.getBuName(user));
                info.put("createTime", DateUtils.fmtLongDate(now));
                info.put("tradeMode", tradeMode);
                info.put("comment", checkRemarkText);
                infos.add(info);
            } else {
                for (UserEmailVo vo : emails.getAll()) {
                    TnEmailInfo info = new TnEmailInfo();
                    info.setRecipient(vo.getEmail());
                    info.setLang("zh");
                    info.setTitle(titlePdocNo + "任务：" + task.getTaskNo() + " eCustoms:  价格审核失败");
                    info.setTemplate("price_review_failed.zh");
                    info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                    info.put("name", vo.getName());
                    info.put("taskNo", task.getTaskNo());
                    info.put("taskUrl", DeclUtils.getTaskUrl(task));
                    info.put("serialNumber", task.getDeclaranceSerialNumber());
                    info.put("declUrl", DeclUtils.getDeclUrl(task));
                    info.put("pdocNo", pdocNo);
                    info.put("sourceBy", sourceBy);
                    info.put("createBy", user.getName());
                    info.put("company", UserUtils.getBuName(user));
                    info.put("createTime", DateUtils.fmtLongDate(now));
                    info.put("tradeMode", tradeMode);
                    info.put("comment", checkRemarkText);
                    infos.add(info);
                }
            }

            EmailStoreMq.putAll(infos);
        }
    }

    @Override
    public HsVo getSplitItem(QueryVo qv) throws IllegalAccessException, InvocationTargetException {
        HsVo vo = new HsVo();
        TdHsItem hsItem = this.hsItemDao.get(qv.getId());
        BeanUtils.copyProperties(vo, hsItem);
        vo.setMainId(hsItem.getId());
        vo.setTdItemId(hsItem.getItemId());
        return vo;
    }

    @Override
    public void nuclearClaspSubmit(Long taskId, String comment, LoginUser user) {
        Date now = new Date();

        TdDeclarationTask task = this.declarationTaskDao.get(taskId);
        TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
        status.setStatus(Constants.Dtask.STATUS_COMPLETED);
        status.setOperation(Constants.Dtask.FTA_CHECK_LIST);
        status.setTaskId(task.getId());
        status.setIsRead(Constants.NO);
        status.setMsg(comment.substring(0, 10));
        DeclUtils.setTaskStatusCompany(status, user);
        status.init(user, now);
        this.declarationTaskStatusDao.save(status);

        //核扣后任务为已完成，单证为已完成
        task.setStatus(Constants.Dtask.STATUS_COMPLETED);
        task.update(user, now);

        this.declarationTaskDao.save(task);
        TdHead head = this.headDao.get(task.getDeclId());
        head.setStatus(Constants.IMP_STATUS_RE_CHECK);
        head.update(user, now);
        this.headDao.save(head);

        //修改出库单核扣标识
//        List<TdOutboundOrder> outboundOrders = this.outboundOrderoDao.findByTaskId(task.getId());
//        if (Utils.isNotEmpty(outboundOrders)) {
//            for (TdOutboundOrder outboundOrder : outboundOrders) {
//                outboundOrder.setCheckMark(Constants.YES);
//            }
//            this.outboundOrderoDao.saveAll(outboundOrders);
//        }
    }

    @Override
    @Transactional
    public String splitItem(ListItemVo vo, LoginUser user) throws InvocationTargetException, IllegalAccessException {
        Date now = new Date();
        TdHsItem item = this.hsItemDao.get(vo.getMainId()); // 拆分前对象
        TdHsItem source = new TdHsItem(); // 记录拆分前对象数据
        BeanUtils.copyProperties(source, item);
        if (null == vo.getQtylist() || vo.getQtylist().size() <= 0) {
            return null;
        }
        //--------------------保存原项目剩余数量 start----------------------------------------
        item.setQuantity(Utils.parseBigDecimal(vo.getQuantity())); // 数量
        item.setOriginCountry(vo.getOriginCountry()); // 原产国
        item.setBatchNo(vo.getBatchNo()); // 批次号
        BigDecimal splitQuantity = new BigDecimal(vo.getQuantity()); // 拆分后数量
        BigDecimal sourceQuantity = source.getQuantity(); // 原项目数量
        // 报关单证行项目拆分时，法定数量、法定第二数量、总价、毛重、净重字段分摊计算逻辑更新: （指定字段原数值/原项目数量）*拆分后数量
        // 计量单位1
        if (Utils.isNotNull(item.getUnitQty())) {
            BigDecimal unitQty = item.getUnitQty().divide(sourceQuantity, 5).multiply(splitQuantity);
            item.setUnitQty(unitQty.setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        // 计量单位2
        if (Utils.isNotNull(item.getUnitQty2())) {
            BigDecimal unitQty2 = item.getUnitQty2().divide(sourceQuantity, 5).multiply(splitQuantity);
            item.setUnitQty2(unitQty2.setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        // 净重
        if (Utils.isNotNull(item.getNetWeight())) {
            BigDecimal netWeight = item.getNetWeight().divide(sourceQuantity, 5).multiply(splitQuantity);
            item.setNetWeight(netWeight.setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        // 毛重
        if (Utils.isNotNull(item.getGrossWeight())) {
            BigDecimal grossWeight = item.getGrossWeight().divide(sourceQuantity, 5).multiply(splitQuantity);
            item.setGrossWeight(grossWeight.setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        // 总价
        if (Utils.isNotNull(item.getCurrencyAmount())) {
            BigDecimal currencyAmount = item.getCurrencyAmount().divide(sourceQuantity, 5).multiply(splitQuantity);
            item.setCurrencyAmount(currencyAmount.setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        item.setUpdateBy(user.getUserNo());
        item.setUpdateTime(now);
        item.setItemMark(Constants.ItemType.ITEM_SPLIT); // 拆分标识
        this.hsItemDao.save(item); // 保存原项目拆分剩余对象
        //------------------保存原对象关系 start--------
        ToItemRelationship old = this.toItemRelationshipDao.searchSourceItem(item.getId(), vo.getSplitType());
        ToItemRelationship toItemRelationship = new ToItemRelationship();
        if (null != old) {
            old.setCurrentVersion(Constants.NO);
            old.update(user, now);
            this.toItemRelationshipDao.save(old); // 修改原关系表中的版本
        } else {
            old = this.toItemRelationshipDao.searchSourceItemIgnoreVersion(item.getId(), vo.getSplitType());
        }
        if (null != old) {
            BeanUtils.copyProperties(toItemRelationship, old);
            // 清单类型
            toItemRelationship.setListType(vo.getSplitType());
            toItemRelationship.setVersion(old.getVersion() + 1);
            toItemRelationship.setVersionId(old.getId());
            toItemRelationship.setOperation(Constants.ItemType.ITEM_SPLIT);
            toItemRelationship.setCurrentVersion(Constants.YES);
            toItemRelationship.setQuantity(item.getQuantity());
            toItemRelationship.init(now);
            this.toItemRelationshipDao.save(toItemRelationship); // 拆分后最新版本
        }
        //------------------保存原对象关系 end----------
        //------------------保存原项目拆分记录 start--------
        // 源对象是否有拆分记录
//        List<ToItemSplitMergeRecord> list = new ArrayList<>();
        ToItemSplitMergeRecord entity = null;
        ToItemSplitMergeRecord record = toItemSplitMergeRecordDao.searchSourceRecord(item.getId());
        if (null != record) {
            entity = new ToItemSplitMergeRecord();
            record.setCurrentVersion(Constants.NO);
            record.update(user, now);
            this.toItemSplitMergeRecordDao.save(record);
            BeanUtils.copyProperties(entity, record);
            entity.init(user, now);
            entity.setOperation(Constants.ItemType.ITEM_SPLIT);
            entity.setSourceId(item.getId());
            entity.setItemId(item.getId());
            entity.setVersionId(record.getId());
            entity.setCurrentVersion(Constants.YES);
            // 清单类型判断
            entity.setListType(vo.getSplitType());
        } else {
            entity = new ToItemSplitMergeRecord();
            entity.init(user, now);
            entity.setHeadId(item.getHeadId()); // 主表ID
            entity.setSourceId(item.getId()); // 原对象ID
            entity.setItemId(item.getId()); // 拆分后ID
            entity.setOperation(Constants.ItemType.ITEM_SPLIT); // 动作
            entity.setListType(vo.getSplitType());
            entity.setCurrentVersion(Constants.YES);
        }
        this.toItemSplitMergeRecordDao.save(entity);
        //------------------保存原项目拆分记录 end----------
        //--------------------保存原项目剩余数量 end----------------------------------------
        //--------------------保存拆分对象 start----------------------------------------
        for (BaseQty qty : vo.getQtylist()) {
            if (null != qty) {
//                BigDecimal splitRatio = StringAmountCalUtil.divide(qty.getQuantity(), sourceQuantity, 4);
                TdHsItem toItem = new TdHsItem();
                org.springframework.beans.BeanUtils.copyProperties(item, toItem, new String[]{"id"});
                // 拆分数量
                toItem.setQuantity(qty.getQuantity());
                toItem.setOriginCountry(qty.getOriginCountry()); // 原产国
                toItem.setBatchNo(qty.getBatchNo()); // 批次号

                if (Utils.isNotNull(source.getUnitQty())) {
                    BigDecimal unitQty = source.getUnitQty().divide(sourceQuantity, 5).multiply(qty.getQuantity());
                    toItem.setUnitQty(unitQty.setScale(4, BigDecimal.ROUND_HALF_UP));
                }
                if (Utils.isNotNull(source.getUnitQty2())) {
                    BigDecimal unitQty2 = source.getUnitQty2().divide(sourceQuantity, 5).multiply(qty.getQuantity());
                    toItem.setUnitQty2(unitQty2.setScale(4, BigDecimal.ROUND_HALF_UP));
                }
                // 净重
                if (Utils.isNotNull(source.getNetWeight())) {
                    BigDecimal netWeight = source.getNetWeight().divide(sourceQuantity, 5).multiply(qty.getQuantity());
                    toItem.setNetWeight(netWeight.setScale(4, BigDecimal.ROUND_HALF_UP));
                }
                // 毛重
                if (Utils.isNotNull(toItem.getGrossWeight())) {
                    BigDecimal grossWeight = source.getGrossWeight().divide(sourceQuantity, 5).multiply(qty.getQuantity());
                    toItem.setGrossWeight(grossWeight.setScale(4, BigDecimal.ROUND_HALF_UP));
                }
                // 计算总价
                if (Utils.isNotNull(source.getCurrencyAmount())) {
                    BigDecimal currencyAmount = source.getCurrencyAmount().divide(sourceQuantity, 5).multiply(qty.getQuantity());
                    toItem.setCurrencyAmount(currencyAmount.setScale(4, BigDecimal.ROUND_HALF_UP));
                    if (StringUtil.isNotBlank(Utils.white(toItem.getQuantity()))) {
                        toItem.setUnitPrice(toItem.getCurrencyAmount().divide(toItem.getQuantity(), 4, BigDecimal.ROUND_HALF_UP));
                    }
                }
                this.hsItemDao.save(toItem); // 保存拆分对象
                this.hsItemDao.updateItemNo(getItemNo(toItem.getHeadId()), toItem.getId());
                this.splitCopyAttribute(item.getId(), user, now, toItem.getId(), item.getHeadId());
                /************报关单拆分后 添加商品补充信息 start*******************/
                TdHsItemSupplement supp = this.hsItemSupplementDao.findByItemId(item.getId());
                TdHsItemSupplement supp2 = new TdHsItemSupplement();
                if (null == supp) {
                    supp2.setHeadId(item.getHeadId());
                } else {
                    BeanUtils.copyProperties(supp2, supp);
                }
                supp2.setItemId(toItem.getId());
                supp2.setCargoAttribute(Utils.join(vo.getCargoAttributes(), Constants.COMMA));
                supp2.init(user, now);
                this.hsItemSupplementDao.save(supp2);

                /**************报关单拆分后 添加商品补充信息 end*****************/
                vo.setResId(toItem.getId());
                //------------------保存拆分对象关系 start--------
                if (null != old) {
                    ToItemRelationship relationship = new ToItemRelationship();
                    BeanUtils.copyProperties(relationship, toItemRelationship);
                    relationship.init(now);
                    relationship.setItemId(toItem.getId());
                    // 清单类型
                    relationship.setListType(vo.getSplitType());
                    relationship.setQuantity(toItem.getQuantity());
                    this.toItemRelationshipDao.save(relationship);
                }
                //------------------保存拆分对象关系 end----------
                // -------------------保存拆分记录 start-----------
                ToItemSplitMergeRecord record1 = new ToItemSplitMergeRecord();
                record1.setListType(vo.getSplitType());
                record1.setHeadId(item.getHeadId());
                record1.setSourceId(item.getId());
                record1.setItemId(toItem.getId());
                record1.setOperation(Constants.ItemType.ITEM_SPLIT);
                record1.init(user, now);
                record1.setCurrentVersion(Constants.YES);
                this.toItemSplitMergeRecordDao.save(record1);
                //------------------保存拆分记录 end----------
            }
        }
        //--------------------保存拆分对象 end----------------------------------------
        return null;
    }

    private void splitCopyAttribute(Long sourceId, LoginUser user, Date now, Long id, Long headId) {
        List<ItemAttributeVo> attributeVos = this.itemAttributeDao.findByItemId(sourceId);
        List<TdItemAttribute> attributes = new ArrayList<>();
        for (ItemAttributeVo attr : attributeVos) {
            attributes.add(attr.convertTdItemAttribute(id, headId, user.getUserNo(), now));
        }
        this.itemAttributeDao.saveAll(attributes);
    }

    /**
     * 获取项号
     *
     * @param headId headId
     * @return 项号
     */
    private Integer getItemNo(Long headId) {
        int i = this.hsItemDao.getItemNoByHeadId(headId);
        if (0 == i) {
            return 1;
        } else {
            return i + 1;
        }
    }

    @Override
    @Transactional
    public String updateDeclItemNo(List<HsItemVo> hsItemVoList, LoginUser user) {
        Date now = new Date();
        if (null == hsItemVoList || hsItemVoList.size() < 1) {
            return null;
        }
        // 查重
        Set<Integer> declItemNos = new HashSet<>();
        boolean flag = false; // 重复标识，默认不重复
        for (HsItemVo item : hsItemVoList) {
            declItemNos.add(item.getItemNo());
        }
        flag = declItemNos.size() != hsItemVoList.size() ? true : false;
        if (flag) {
            return OpResult.DECLARE_ITEM_NO_REPEAT.getCode();
        }
        // 更新报关单商品序号
        for (HsItemVo item : hsItemVoList) {
            this.hsItemDao.updateDeclItemNo(item.getId(), item.getItemNo(), user.getUserNo(), now);
        }
        return null;
    }

    @Override
    @Transactional
    public String uploadTxblDoc(List<MultipartFile> parts, Long listId, Long headId, Long taskId, String docType, LoginUser user) {
        try {
            List<ToDocument> document = this.toDocumentDao.findByTaskId(taskId); // 根据任务id获取随附单据;
            // 定义list保存随附单据的文件名称
            List<String> documentNames = new ArrayList<>();
            if (Utils.isNotEmpty(document)) {
                for (ToDocument name : document) {
                    documentNames.add(name.getDocFileName());
                }
            }
            // 遍历上传文件list,如果存在则为文件重命名
            List<String> fileNewName = new ArrayList<>(); // 定义list保存上传文件的文件名
            if (Utils.isNotEmpty(parts)) {
                for (MultipartFile part : parts) {
                    if (documentNames.contains(part.getOriginalFilename())) {
                        String fileName = part.getOriginalFilename().substring(0, part.getOriginalFilename().lastIndexOf(".")); // 文件前缀
                        String filetype = part.getOriginalFilename().substring(part.getOriginalFilename().lastIndexOf(".") + 1); // 文件后缀
                        String newFileName = fileName + "(" + 1 + ")." + filetype;
                        if (!documentNames.contains(newFileName)) {
                            fileNewName.add(newFileName);
                        } else if (documentNames.contains(newFileName)) {
                            fileNewName.add(this.fileRename(1, newFileName, documentNames));
                        }
                    } else {
                        fileNewName.add(part.getOriginalFilename());
                    }
                }
            }
            if (Utils.isNotEmpty(parts)) {
                String path = DictUtils.getValue(Constants.DictType.FOLDER, Constants.Folder.DOCUMENT_UPLOAD);
                String newPath = null;
                Date now = new Date();
                TdDeclarationTask td = this.declarationTaskDao.get(taskId);
                List<String> files = new ArrayList<>();
                String docpath = null;
                String docNo = "";
                for (int i = 0; i < parts.size(); i++) {
                    newPath = FileUtil.createPath(path);
                    docNo = DateUtils.fmtDate8(new Date()) + Utils.randomNumber(6);
                    DocumentUtils.uploadDocument(parts.get(i), fileNewName.get(i), newPath, null, taskId, docNo, docType, false, "T");
                    Long fileId = FileRecordUtils.save(fileNewName.get(i), newPath, Constants.FILE_RECORD_TYPE_UPLOAD, "import.to.document", user.getUserNo());
                    files.add(newPath + File.separator + fileNewName.get(i));

                    // 任务记录新增动作
                    if (taskId != null) {
                        TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
                        status.init(user, now);
                        status.setTaskId(taskId);
                        status.setOperation(Constants.Dtask.OPER_UPLOAD_TAXDOC);
                        status.setLinkId(fileId);
                        status.setLinkContent(fileNewName.get(i));
                        status.setIsRead(Constants.NO);
                        DeclUtils.setTaskStatusCompany(status, user);
                        this.declarationTaskStatusDao.save(status);
                    }
                }
                // 发送邮件通知
                TdDeclarationTask task = this.declarationTaskDao.get(taskId);
                TdHead tdHead = this.headDao.get(task.getDeclId());

                //获取税单
                String filename = "";
                String zipPath = "";
                if (Utils.isNotEmpty(files)) {
                    if (files.size() > 1) {
                        String dir = FileUtil.createPath(DictUtils.getValue(Constants.DictType.FOLDER, Constants.Folder.DECL_ZIP));
                        FileUtil.createDir(dir);
                        zipPath = dir + File.separator + td.getTaskNo() + ".zip";
                        FileUtil.zip(zipPath, files);
                        filename = td.getTaskNo() + ".zip";
                    } else {
                        zipPath = newPath + File.separator + fileNewName.get(0);
                        filename = fileNewName.get(0);
                    }
                }

                // 组装邮件发送信息
                // 发送通知邮件通知所有关联该任务的人员
                // 任务关系用户
                List<Long> user1 = (List<Long>) this.userDeclarationTaskDao.getUserIdListByTaskId(taskId);
                // 转派用户
                List<Long> user2 = (List<Long>) this.transferUserDao.getUserIdListByTaskId(taskId);
                Set<Long> users = new HashSet<>();
                // 任务创建用户
                users.add(this.userDao.findIdByUserNo(td.getCreateBy()));
                users.add(user.getId()); // 当前操作附件替换用户
                users.addAll(user1); // 任务关系用户
                users.addAll(user2); // 转派用户
                //上传应税附件邮件接收开关
                users = UserUtils.filter(users, Constants.User.PARA_ADD_TAX_DOCUMENT_EMAIL);
                // 根据用户id获取邮箱
                List<TsUserInfo> list = this.userDao.findAllByIdIn(users);


                List<TnEmailInfo> infos = new ArrayList<>();
//                TsUserInfo userInfo = this.userDao.findByUserNo(task.getCreateBy());


                //获取字典邮箱
                List<TmDataDict> solvayEmials = this.dictDao.findByTypeIdOrderByCodeAsc(Constants.DictType.TAX_BILL_EMAIL);
                if (Utils.isNotEmpty(solvayEmials)) {
                    for (TmDataDict apemail : solvayEmials) {
                        TsUserInfo emailVo = new TsUserInfo();
                        emailVo.setName(apemail.getName());
                        emailVo.setEmail(apemail.getValue());
                        emailVo.setType(Constants.User.TYPE_NORMAL);
                        emailVo.setLanguage(Constants.LANGUAGE_ZH);
                        list.add(emailVo);
                    }
                }

                // 发送邮件
                String entryId = "";
                if (tdHead != null) {
                    entryId = Utils.white(tdHead.getEntryId());
                }
                // 发送邮件
                if (Utils.isNotEmpty(list)) {
                    String em = "";
                    for (TsUserInfo vo : list) {
                        if (StringUtil.isNotBlank(vo.getEmail()) && vo.getType().equals(Constants.User.TYPE_NORMAL)) {
                            if ("".equals(em)) {
                                em = vo.getEmail();
                            } else {
                                em = em + ";" + vo.getEmail();
                            }
                        }
                    }
                    TnEmailInfo info = new TnEmailInfo();
                    info.setRecipient(em);
                    info.setLang("zh");
                    info.setAttachment(zipPath);
                    info.setAttachmentFilename(filename);
                    info.setTitle("应税附件已上传 " + entryId + " /" + task.getTaskNo());
                    info.setTemplate("customs_tax_doc_upload.zh");
                    info.put("fronturl", DictUtils.getValue(Constants.DictType.EMAIL, Constants.DICT_CODE_EMAIL_FRONT_BASE_URL));
                    info.put("taskNo", task.getTaskNo());
                    info.put("taskUrl", DeclUtils.getTaskUrl(task));
                    info.put("serialNumber", task.getDeclaranceSerialNumber());
                    info.put("declUrl", DeclUtils.getDeclUrl(task));
                    info.put("createBy", user.getName());
                    info.put("company", UserUtils.getBuName(user));
                    info.put("createTime", DateUtils.fmtLongDate(now));
                    infos.add(info);
                }

                EmailStoreMq.putAll(infos);
            }
        } catch (IOException e) {
            Log.error(e);
            return OpResult.FAILED.getCode();
        }
        return OpResult.OK.getCode();
    }

    @Override
    @Transactional
    public String updateItemPartInfo(QueryVo qv) {
        TdHead head = this.headDao.get(qv.getId());
        this.listService.updateItemPartInfo(qv, head);
        return null;
    }

    public String taxableExcel(QueryVo qv, LoginUser user) throws Exception {
        List<TdTaxableGtsItemExcelVo> list = this.tdTaxableGtsItemService.excelList(qv, user);
        if (Utils.isNotEmpty(list)) {
            String fileName = "分拨费用分摊表_" + DateUtils.getCurrentLongDate();
            String filePath = DictUtils.getValue(Constants.DictType.FOLDER, Constants.Folder.DOCUMENT_SYSGEN) + File.separator + FileUtil.createPath();
            FileUtils.forceMkdir(new File(filePath));
            File file = new File(filePath + File.separator + fileName + ".xls");
            filePath = filePath + File.separator + fileName + ".xls";
            new ExportExcelUtil().listToExcel(file, list);
            return filePath;
        }
        return null;

    }

    @Override
    @Transactional
    public String updatePakgeUnit(QueryVo qv, LoginUser user) {
        TdHead head = this.headDao.get(qv.getId());
        if (Utils.isNotNull(head)) {
            if (null != head.getOwnerId() && null != head.getDeptId()) {
                List<TdHsItem> itemList = this.hsItemDao.findByHeadId(head.getId());
                Date now = new Date();
                List<String> list = new ArrayList<>();
                if (Utils.isNotEmpty(itemList)) {
                    for (TdHsItem item : itemList) {
                        StringBuilder str = new StringBuilder();
                        TmPart part = this.partDao.findAllByCompanyAndDeptAndPartNum(item.getProductNo(), head.getOwnerId(), head.getDeptId());
                        part = DeptPartUtil.switchPart(part, item.getProductNo(), head.getOwnerId(), head.getDeptId());
                        if (null != part) {
                            // 获取包装维护数据
                            TmPartPackageMaintain tmPartPackageMaintain = this.tmPartPackageMaintainDao.findByItemIdAndActive(part.getId(), Constants.YES);
                            if (null != tmPartPackageMaintain) {
                                if (null != item.getUnit() && null != tmPartPackageMaintain.getUnit()) {
                                    // 比较成交单位与包装维护单位是否一致
                                    if (item.getUnit().equals(tmPartPackageMaintain.getUnit())) {
                                        if (StringUtil.isNotBlank(tmPartPackageMaintain.getPkgType())) {
                                            item.setDeclareUnit(tmPartPackageMaintain.getPkgType());
                                        } else {
                                            str.append(item.getItemNo() + ", 物料包装维护法定单位为空;");
                                        }
                                        // 包装规格转换  成交数量= 报关商品成交数量/包装维护基础规格数量
                                        if ((null != item.getQuantity() && 1 == item.getQuantity().compareTo(BigDecimal.ZERO)) && (null != tmPartPackageMaintain.getPackages() && 1 == tmPartPackageMaintain.getPackages().compareTo(BigDecimal.ZERO))) {
                                            BigDecimal decimal = item.getQuantity().divide(tmPartPackageMaintain.getPackages(), 5, BigDecimal.ROUND_HALF_UP);
                                            item.setQuantity(decimal);
                                            if (null != item.getCurrencyAmount()) {
                                                //重新计算单价
                                                item.setUnitPrice(item.getCurrencyAmount().divide(item.getQuantity(), 4, BigDecimal.ROUND_HALF_UP));
                                                item.update(user, now);
                                                this.hsItemDao.save(item);
                                            } else {
                                                str.append(item.getItemNo() + ",报关商品总价为空;");
                                            }
                                        } else {
                                            str.append(item.getItemNo() + ",报关商品法定数量或物料包装维护法定数量为空;");
                                        }
                                    } else {
                                        str.append(item.getItemNo() + ",报关单商品法定单位与包装维护法定单位不一致;");
                                    }
                                } else {
                                    str.append(item.getItemNo() + ",报关单商品法定单位或物料包装维护法定单位为空;");
                                }
                            } else {
                                item.setQuantity(item.getUnitQty());
                                item.setDeclareUnit(item.getUnit());
                                if (null != item.getCurrencyAmount() && 1 == item.getQuantity().compareTo(BigDecimal.ZERO)) {
                                    item.setUnitPrice(item.getCurrencyAmount().divide(Utils.parseBigDecimal(item.getQuantity()), 4, BigDecimal.ROUND_HALF_UP));
                                }
                                this.hsItemDao.save(item);
                                str.append(item.getItemNo() + ",包装主数据未维护");
                            }
                        } else {
                            str.append(item.getItemNo() + ",物料主数据未维护;");
                        }
                        if (StringUtil.isNotBlank(str.toString())) {
                            list.add(str.toString());
                        }
                    }
                    if (Utils.isNotEmpty(list)) {
                        return Utils.join(list, Constants.CSV_RN);
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    private void saveOcrData(OcrRespVo vo, Long taskId, Long declId, LoginUser user, Date now) {
        if (null != vo) {
            DataVo data = vo.getData();
            if (null != data) {
                TdDetailOcr detailOcr = new TdDetailOcr();
                // 是否已经存在
                TdDetailOcr byTaskIdAndCuAndCurrentVersion = this.ocrDetailDao.findByTaskIdAndCurrentVersion(taskId, Constants.YES);
                if (null != byTaskIdAndCuAndCurrentVersion) {
                    // 修改当前版本
                    this.ocrDetailDao.updateCurrentVersionByTaskId(taskId);
                    this.ocrHsItemDao.updateCurrentVersionByTaskId(taskId);
                }
                List<TdHsItemOcr> list = new ArrayList<>();
                detailOcr.init(user, now);
                detailOcr.setDeclId(declId);
                detailOcr.setTaskId(taskId);
                detailOcr.setVersion(this.ocrDetailDao.getMaxVersionByTaskId(taskId) + 1);
                detailOcr.setCurrentVersion(Constants.YES);
                this.ocrDetailDao.save(detailOcr);
                this.swithEntity(data, detailOcr, list, taskId, declId, user, now);
                if (Utils.isNotEmpty(list)) {
                    this.ocrHsItemDao.saveAll(list);
                }
            }
        }
    }

    private void swithEntity(DataVo origin, TdDetailOcr detailOcr, List<TdHsItemOcr> list, Long taskId, Long declId, LoginUser user, Date now) {
        detailOcr.setPreEntryId(origin.getPre_Entry_Id());
        detailOcr.setEntryId(origin.getEntry_Id());
        detailOcr.setDeclPort(origin.getDecl_Port());
        detailOcr.setTradeName(origin.getTrade_Name());
        detailOcr.setTradeCoScc(origin.getTrade_Co_Scc());
        detailOcr.setIePort(origin.getIE_Port());
        detailOcr.setIeDate(origin.getIE_Date());
        detailOcr.setDDate(origin.getD_Date());
        detailOcr.setManualNo(origin.getManual_No());
        detailOcr.setOverseasConsigneeCode(origin.getOverseasConsigneeCode());
        detailOcr.setOverseasConsigneeeName(origin.getOverseasConsigneeeName());
        detailOcr.setTrafMode(origin.getTraf_Mode());
        detailOcr.setTrafName(origin.getTraf_Name());
        detailOcr.setVoyageNo(origin.getVoyage_No());
        detailOcr.setBillNo(origin.getBill_No());
        detailOcr.setGoodsPlace(origin.getGoodsPlace());
        detailOcr.setOwnerName(origin.getOwner_Name());
        detailOcr.setOwnerCodeScc(origin.getOwner_Code_Scc());
        detailOcr.setTradeMode(origin.getTrade_Mode());
        detailOcr.setCutMode(origin.getCut_Mode());
        detailOcr.setLicenseNo(origin.getLicense_No());
        detailOcr.setDeparturePort(origin.getDeparture_Port());
        detailOcr.setContrNo(origin.getContr_No());
        detailOcr.setTradeAreaCode(origin.getTrade_Area_Code());
        detailOcr.setTradeCountry(origin.getTrade_Country());
        detailOcr.setDistinatePort(origin.getDistinate_Port());
        detailOcr.setEntyPortCode(origin.getEntyPortCode());
        detailOcr.setWrapType(origin.getWrap_Type());
        detailOcr.setPackNo(origin.getPack_No());
        detailOcr.setGrossWt(origin.getGross_Wt());
        detailOcr.setNetWt(origin.getNet_Wt());
        detailOcr.setTransMode(origin.getTrans_Mode());
        detailOcr.setFee(origin.getFee());
        detailOcr.setInsur(origin.getInsur());
        detailOcr.setOther(origin.getOther());
        detailOcr.setDocuCodeCount(origin.getDocu_Code_Count());
        detailOcr.setRmk(origin.getRmk());
        detailOcr.setMarkNo(origin.getMarkNo());
        if (null != origin.getDeclItem() && origin.getDeclItem().size() > 0) {
            for (OcrItemVo vo : origin.getDeclItem()) {
                TdHsItemOcr tdHsItemOcr = new TdHsItemOcr();
                tdHsItemOcr.setTaskId(taskId);
                tdHsItemOcr.setDeclId(declId);
                tdHsItemOcr.init(user, now);
                tdHsItemOcr.setCurrentVersion(Constants.YES);
                tdHsItemOcr.setVersion(detailOcr.getVersion());
                this.switchItem(vo, tdHsItemOcr);
                list.add(tdHsItemOcr);
            }
        }
    }

    private void switchItem(OcrItemVo vo, TdHsItemOcr itemOcr) {
        itemOcr.setGNo(vo.getG_No());
        itemOcr.setContrItem(vo.getContr_Item());
        itemOcr.setCodeT(vo.getCode_T());
        itemOcr.setGName(vo.getG_Name());
        itemOcr.setGModel(vo.getG_Model());
        itemOcr.setGQty(vo.getG_Qty());
        itemOcr.setGUnitName(vo.getG_Unit_Name());
        itemOcr.setQty1(vo.getQty_1());
        itemOcr.setUnit1Name(vo.getUnit_1_Name());
        itemOcr.setQty2(vo.getQty_2());
        itemOcr.setUnit2Name(vo.getUnit_2_Name());
        itemOcr.setDeclPrice(vo.getDecl_Price());
        itemOcr.setTradeTotal(vo.getTrade_Total());
        itemOcr.setTradeCurrName(vo.getTrade_Curr_Name());
        itemOcr.setOriginCountryName(vo.getOrigin_Country_Name());
        itemOcr.setDestinationCountryName(vo.getDestination_Country_Name());
        itemOcr.setDistrictName(vo.getDistrictName());
        itemOcr.setDutyModeName(vo.getDuty_Mode_Name());
        itemOcr.setDestCode(vo.getDestCode());
        itemOcr.setDDate(vo.getD_Date());
        itemOcr.setIsSpecial(vo.getIs_Special());
        itemOcr.setIsFeeAffect(vo.getIs_FeeAfect());
        itemOcr.setIsPay(vo.getIs_Pay());
    }

    @Transactional
    public String resendFile(DeclaranceTaskVo vo, LoginUser user) throws IOException {
        String url = DictUtils.getValue(Constants.DictType.INTERFACE, Constants.Interface.MEIHUA_OCR_URL);
        Date now = new Date();
        TlInterfaceLog log = new TlInterfaceLog();
        log.setCreateBy(user.getUserNo());
        log.setCreateTime(now);
        // 任务
        TdDeclarationTask task = this.declarationTaskDao.get(vo.getId());
        // 发送的校对稿文件
        TlFileRecord fileRecord = this.fileRecordDao.get(vo.getLinkId2());
        MultipartFile file = this.fileToMultipartFile(vo.getLinkId2(), "application/pdf");
        // OCR保存的文件
        MeiHuaOCRPort.send(url, task.getTaskNo(), log, file, fileRecord.getPath());
        OcrRespVo respVo = MeiHuaOCRPort.parse(log.getResp());
        if (null != respVo) {
            if ("1".equals(respVo.getStatus())) {
                log.setStatus(Constants.Log.SUCCEED);
            } else {
                log.setStatus(Constants.Log.FAILED);
            }
        }
        this.interfaceLogDao.save(log);
        TlFileRecord record = this.fileRecordDao.get(vo.getLinkId());
        // OCR接口返回数据替换原文件
        this.relpaceOcrFile(Utils.white(log.getResp()), task.getStatus(), record.getPath(), user, task.getId(), now, vo.getLinkId2(), fileRecord.getFileName(), record.getFileName());
        // OCR接口数据保存
        this.saveOcrData(respVo, task.getId(), task.getDeclId(), user, now);
        return null;
    }

    /**
     * <p>Description: File转成MultipartFile</p>
     *
     * @param fileId      记录ID
     * @param contentType 文件转换类型
     * @return MockMultipartFile
     * @throws IOException IOException
     */
    private MultipartFile fileToMultipartFile(Long fileId, String contentType) throws IOException {
        TlFileRecord tlFileRecord = FileRecordUtils.get(fileId);
        String path = tlFileRecord.getPath() + "/" + tlFileRecord.getFileName();
        File file = new File(path);
        FileInputStream input = new FileInputStream(file);
        if (file.getName().contains(".pdf")) {
            contentType = "application/pdf";
        } else if (file.getName().contains(".xls") || file.getName().contains(".xlsx")) {
            contentType = "application/octet-stream";
        }
        return new MockMultipartFile("file", file.getName(), contentType, IOUtils.toByteArray(input));
    }

    /**
     * OCR返回数据替换已生成文件内容
     *
     * @param data       文件内容
     * @param taskStatus 任务状态
     * @param path       路径
     * @param oldName    文件名
     * @param user       用户
     * @param taskId     任务ID
     * @param now        时间
     * @param sendId     重新发送文件ID
     * @param sendName   文件名
     */
    private void relpaceOcrFile(String data, String taskStatus, String path, LoginUser user, Long taskId, Date now, Long sendId, String sendName, String oldName) {
        path = path + File.separator + oldName;
        FileUtil.write(path, data, "utf-8");
        TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
        status.setTaskId(taskId);
        status.setStatus(taskStatus);
        status.setLinkId(sendId);
        status.setLinkContent(sendName);
        status.setOperation(Constants.Dtask.OCR_RESEND_FILE);
        status.setIsRead(Constants.NO);
        DeclUtils.setTaskStatusCompany(status, user);
        status.init(user, now);
        this.declarationTaskStatusDao.save(status);
    }

    @Override
    public DraftVo getDraft(Long id, Long taskId) {
        DraftVo draftVo = new DraftVo();
        TdHead tdHead = this.headDao.get(id);
        draftVo.setBusinessType(tdHead.getBusinessType());
        TdDetail tdDetail = this.detailDao.findByHeadId(id);
        draftVo.setPreEntryId(Utils.space(tdDetail.getPreEntryId())); // 预录入编号
        draftVo.setEntryId(Utils.space(tdHead.getEntryId())); // 海关编号
        if (StringUtil.isNotBlank(tdDetail.getDeclarationCustoms())) {
            TpCustoms tpCustoms = this.tpCustomsDao.findByCode(tdDetail.getDeclarationCustoms());
            draftVo.setDeclarationCustomsDesc(Utils.space(tpCustoms.getName())); // 申报地海关名称
        }
        // TYPE = 'D' -- D表示境内收发货人
        // TYPE = 'E' -- E表示境外收发货人
        // TYPE = 'I' -- I表示消费使用单位
        // TYPE = 'R' -- R表示申报单位
        TdPartner tdPartner = this.partnerDao.findByHeadIdAndType(id, "D");
        if (tdPartner != null) {
            draftVo.setSocialCreditCode1(Utils.space(tdPartner.getSocialCreditCode())); // 境内收发货人-社会信用代码
            draftVo.setPartnerName1(Utils.space(WordUtils.reword(tdPartner.getPartnerName()))); // 境内收发货人-名称
        }


        TdFee tdFee = this.feeDao.findByHeadId(id);
        if (StringUtil.isNotBlank(tdDetail.getEntryCustoms())) {
            TpCustoms tpCustoms = this.tpCustomsDao.findByCode(tdDetail.getEntryCustoms());
            if (tpCustoms != null) {
                draftVo.setEntryCustomsName(Utils.space(tpCustoms.getName())); // 进境关别 -名称
            }
        }
        draftVo.setEntryCustoms(Utils.space(tdDetail.getEntryCustoms())); // 进境关别
        // 编码
        Date ieDate = tdDetail.getIeDate();
        String importDate = null;
        if (ieDate != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); // Date指定格式：yyyy-MM-dd
            importDate = simpleDateFormat.format(ieDate);
        }
        draftVo.setImportDate(Utils.space(importDate)); // 进出口日期
        // 运输信息
        TdTransportation transportation = this.transportationDao.findByHeadId(id);
        Date declare = transportation.getDeclareDate();
        String declareDate = null;
        if (declare != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); // Date指定格式：yyyy-MM-dd
            declareDate = simpleDateFormat.format(declare);
        }
        draftVo.setDeclareDate(Utils.space(declareDate)); // 申报日期
        draftVo.setRecordNumber(Utils.space(tdDetail.getRecordNumber())); // 备案号
        tdPartner = this.partnerDao.findByHeadIdAndType(id, "E");
        if (tdPartner != null) {
            draftVo.setSocialCreditCode2(Utils.space(tdPartner.getSocialCreditCode())); // 境外收发货人-社会信用代码
            draftVo.setPartnerName2(Utils.space(WordUtils.reword(tdPartner.getPartnerName()))); // 境外收发货人-名称
        }

        draftVo.setTransportType(Utils.space(tdDetail.getTransportType())); // 运输方式
        if (StringUtil.isNotBlank(tdDetail.getTransportType())) {
            draftVo.setTransportTypeDesc(Utils.space(DictUtils.getValue(Constants.DictType.P_TRANSPORTATION_MODE, tdDetail.getTransportType()))); // 运输方式名称
        }
        draftVo.setTransportName(Utils.space(tdDetail.getTransportName())); // 运输工具名称
        draftVo.setVoyageNo(Utils.space(tdDetail.getVoyageNo())); // 航次号
        draftVo.setBillNo(Utils.space(tdDetail.getBillNo())); // 提运单号
        draftVo.setGoodsLocation(Utils.space(transportation.getGoodsLocation())); // 货物存放地点

        tdPartner = this.partnerDao.findByHeadIdAndType(id, "I");
        if (tdPartner != null) {
            draftVo.setSocialCreditCode3(Utils.space(tdPartner.getSocialCreditCode())); // 消费使用单位-社会信用代码
            draftVo.setPartnerName3(Utils.space(WordUtils.reword(tdPartner.getPartnerName()))); // 消费使用单位-名称
        }
        draftVo.setTradeMode(Utils.space(transportation.getTradeMode())); // 监管方式
        if (StringUtil.isNotBlank(transportation.getTradeMode())) {
            draftVo.setTradeModeDesc(Utils.space(DictUtils.getValue(Constants.DictType.P_SUPERVISION_MODE, transportation.getTradeMode()))); // 监管方式名称
        }
        draftVo.setCutMode(Utils.space(tdDetail.getCutMode())); // 征免性质
        if (StringUtil.isNotBlank(tdDetail.getCutMode())) {
            draftVo.setCutModeDesc(Utils.space(DictUtils.getValue(Constants.DictType.P_CUT_MODE, tdDetail.getCutMode()))); // 征免性质名称
        }
        draftVo.setLicenseKey(Utils.space(tdDetail.getLicenseKey())); // 许可证号

        if (StringUtil.isNotBlank(transportation.getPod())) {
            TpPort tpPort = this.portDao.findByCode(transportation.getPod());
            if (tpPort != null) {
                draftVo.setPodDesc(Utils.space(tpPort.getName())); // 启运港名称
            }
            draftVo.setPod(Utils.space(transportation.getPod())); // 启运港
        }
        draftVo.setContratNumber(Utils.space(tdDetail.getContratNumber())); // 合同协议号

        if (StringUtil.isNotBlank(transportation.getTradeNation())) {
            TpCountryRegin tpCountryRegin = this.tpCountryReginDao.findByCode(transportation.getTradeNation());
            draftVo.setTradeNationDesc(Utils.space(tpCountryRegin.getName()));  // 贸易国别（地区）名称
        }
        draftVo.setTradeNation(Utils.space(transportation.getTradeNation())); // 贸易国别（地区）

        if (StringUtil.isNotBlank(transportation.getTradeCountry())) {
            TpCountryRegin tpCountryRegin = this.tpCountryReginDao.findByCode(transportation.getTradeCountry());
            draftVo.setTradeCountryDesc(Utils.space(tpCountryRegin.getName())); // 启运国国名称
        }
        draftVo.setTradeCountry(Utils.space(transportation.getTradeCountry())); // 启运国国

        if (StringUtil.isNotBlank(transportation.getPoa())) {
            TpPort tpPort = this.portDao.findByCode(transportation.getPoa());
            if (tpPort != null) {
                draftVo.setPoaDesc(Utils.space(tpPort.getName())); // 经停港名称
            }
        }
        draftVo.setPoa(Utils.space(transportation.getPoa())); // 经停港

        if (StringUtil.isNotBlank(transportation.getArrivalLocation())) {
            TpDomesticPort tpDomesticPort = this.tpDomesticPortDao.findByCode(transportation.getArrivalLocation());
            draftVo.setArrivalLocationDesc(Utils.space(tpDomesticPort.getName())); // 入境口岸名称
        }
        draftVo.setArrivalLocation(Utils.space(transportation.getArrivalLocation())); // 入境口岸

        draftVo.setPackageType(Utils.space(tdDetail.getPackageType())); // 包装种类
        String packageTypeDesc = "";
        if (StringUtil.isNotBlank(tdDetail.getPackageType())) {
            packageTypeDesc = DictUtils.getValue(Constants.DictType.P_PACKAGE_TYPE, tdDetail.getPackageType());
        }
        draftVo.setPackageTypeDesc(Utils.space(packageTypeDesc)); // 包装种类名称

        //其他包装
        String otherPacking = "";
        String packageType = Utils.space(tdDetail.getPackageType());
        if (StringUtil.isNotBlank(tdDetail.getNoOtherPackaging()) && tdDetail.getNoOtherPackaging().equals("0")) {
            otherPacking = tdDetail.getOtherPackaging();
            if (StringUtil.isNotBlank(otherPacking)) {
                if (StringUtil.isNotBlank(tdDetail.getPackageType())) {
                    packageType = tdDetail.getPackageType() + "/" + otherPacking.replaceAll(",", "/");
                } else {
                    packageType = otherPacking.replaceAll(",", "/");
                }
                String[] list = otherPacking.split(",");
                for (int i = 0; i < list.length; i++) {
                    if (StringUtil.isNotBlank(packageTypeDesc)) {
                        packageTypeDesc = packageTypeDesc + "/" + DictUtils.getValue(Constants.DictType.P_PACKAGE_TYPE, list[i]);
                    } else {
                        packageTypeDesc = DictUtils.getValue(Constants.DictType.P_PACKAGE_TYPE, list[i]);
                    }

                }
            }
        }
        draftVo.setPackageType(Utils.space(packageType));
        draftVo.setPackageTypeDesc(Utils.space(packageTypeDesc));
        if (null != tdDetail.getQuantity()) {
            draftVo.setTotalPackageNumber(Utils.space(tdDetail.getQuantity().stripTrailingZeros())); // 件数
        }
        if (null != transportation.getTotalGrossweight()) {
            draftVo.setTotalGrossweight(Utils.space(transportation.getTotalGrossweight().stripTrailingZeros()));
        }
        if (null != transportation.getTotalNetweight()) {
            draftVo.setTotalNetweight(Utils.space(transportation.getTotalNetweight().stripTrailingZeros()));
        }

        draftVo.setTransMode(Utils.space(transportation.getTransMode())); // 成交方式
        if (StringUtil.isNotBlank(transportation.getTransMode())) {
            draftVo.setTransModeDesc(Utils.space(DictUtils.getValue(Constants.DictType.P_TRANS_MODE, transportation.getTransMode()).replaceAll("&", "&amp;"))); // 成交方式名称
        }

        String freightMarkDesc = null;
        String freightValue = null;
        String freightCurrencyDesc = null;

        String premiumMarkDesc = null;
        String premiumValue = null;
        String premiumCurrencyDesc = null;

        String incidentalMarkDesc = null;
        String incidentalValue = null;
        String incidentalCurrencyDesc = null;
        if (tdFee != null) {
            freightMarkDesc = tdFee.getFreightMark();
            if (null != tdFee.getFreightValue()) {
                freightValue = tdFee.getFreightValue().stripTrailingZeros().toPlainString();
            }
            freightCurrencyDesc = tdFee.getFreightCurrency();

            premiumMarkDesc = tdFee.getPremiumMark();
            if (null != tdFee.getPremiumValue()) {
                premiumValue = tdFee.getPremiumValue().stripTrailingZeros().toPlainString();
            }
            premiumCurrencyDesc = tdFee.getPremiumCurrency();

            incidentalMarkDesc = tdFee.getIncidentalMark();
            if (null != tdFee.getIncidentalValue()) {
                incidentalValue = tdFee.getIncidentalValue().stripTrailingZeros().toPlainString();
            }
            incidentalCurrencyDesc = tdFee.getIncidentalCurrency();
        }

        draftVo.setFreightMarkDesc(Utils.space(tdFee.getFreightMark())); // 运费标识
        draftVo.setFreightValue(Utils.space(freightValue)); // 运费率/价
        draftVo.setFreightCurrencyDesc(Utils.space(freightCurrencyDesc)); // 运费币
        String fee = null;
        // 运费标识 1-费率
        if (StringUtil.isNotBlank(draftVo.getFreightValue()) && StringUtil.isNotBlank(draftVo.getFreightMarkDesc())) {
            fee = "000" + "/" + draftVo.getFreightValue() + "/" + draftVo.getFreightMarkDesc();
        }
        // 运费标识 2-单价 3-总价
        if (StringUtil.isNotBlank(draftVo.getFreightCurrencyDesc()) && StringUtil.isNotBlank(draftVo.getFreightValue()) && StringUtil.isNotBlank(draftVo.getFreightMarkDesc())) {
            fee = draftVo.getFreightCurrencyDesc() + "/" + draftVo.getFreightValue() + "/" + draftVo.getFreightMarkDesc();
        }
        draftVo.setFee(Utils.space(fee));
        draftVo.setPremiumMarkDesc(Utils.space(tdFee.getPremiumMark())); // 保费标识
        draftVo.setPremiumValue(Utils.space(premiumValue));  // 保费率/价
        draftVo.setPremiumCurrencyDesc(Utils.space(premiumCurrencyDesc)); // 保费币种
        String insur = null;
        // 保费标识 1-费率
        if (StringUtil.isNotBlank(draftVo.getPremiumValue()) && StringUtil.isNotBlank(draftVo.getPremiumMarkDesc())) {
            insur = "000" + "/" + draftVo.getPremiumValue() + "/" + draftVo.getPremiumMarkDesc();
        }
        // 保费标识 3-总价
        if (StringUtil.isNotBlank(draftVo.getPremiumCurrencyDesc()) && StringUtil.isNotBlank(draftVo.getPremiumValue()) && StringUtil.isNotBlank(draftVo.getPremiumMarkDesc())) {
            insur = draftVo.getPremiumCurrencyDesc() + "/" + draftVo.getPremiumValue() + "/" + draftVo.getPremiumMarkDesc();
        }
        draftVo.setInsur(Utils.space(insur));
        draftVo.setIncidentalMarkDesc(Utils.space(tdFee.getIncidentalMark())); // 杂费标识
        draftVo.setIncidentalValue(Utils.space(incidentalValue)); // 杂费率/价
        draftVo.setIncidentalCurrencyDesc(Utils.space(incidentalCurrencyDesc)); // 杂费币种
        String other = null;
        // 杂费标识 1-费率
        if (StringUtil.isNotBlank(draftVo.getIncidentalValue()) && StringUtil.isNotBlank(draftVo.getIncidentalMarkDesc())) {
            other = "000" + "/" + draftVo.getIncidentalValue() + "/" + draftVo.getIncidentalMarkDesc();
        }
        // 杂费标识 3-总价
        if (StringUtil.isNotBlank(draftVo.getIncidentalCurrencyDesc()) && StringUtil.isNotBlank(draftVo.getIncidentalValue()) && StringUtil.isNotBlank(draftVo.getIncidentalMarkDesc())) {
            other = draftVo.getIncidentalCurrencyDesc() + "/" + draftVo.getIncidentalValue() + "/" + draftVo.getIncidentalMarkDesc();
        }
        draftVo.setOther(Utils.space(other));
        if (StringUtil.isNotBlank(tdDetail.getSpecialRelation())) {
            draftVo.setSpecialRelationDesc(Utils.space(DictUtils.getValue(Constants.DictType.SPEC_REL_CONFIRM, tdDetail.getSpecialRelation()))); // 特殊关系确认
        }

        if (StringUtil.isNotBlank(tdDetail.getPriceImpact())) {
            draftVo.setPriceImpactDesc(Utils.space(DictUtils.getValue(Constants.DictType.SPEC_REL_CONFIRM, tdDetail.getPriceImpact()))); // 价格影响确认
        }

        if (StringUtil.isNotBlank(tdDetail.getRoyaltyPayment())) {
            draftVo.setRoyaltyPaymentDesc(Utils.space(DictUtils.getValue(Constants.DictType.SPEC_REL_CONFIRM, tdDetail.getRoyaltyPayment()))); // 与货物有关的特许权使用费支付确认
        }
        //随附单据
        QueryVo qv = new QueryVo();
        qv.setId1(taskId);
        List<ToLicense> toLicenses = this.listService.listCertificate(qv);
        if (Utils.isNotEmpty(toLicenses)) {
            int i = 1;
            for (ToLicense vo : toLicenses) {
                if (StringUtil.isNotBlank(draftVo.getDocumentInfo())) {
                    draftVo.setDocumentInfo(draftVo.getDocumentInfo() + "，随附单证" + i + ":" + Utils.white(vo.getDocTypeDesc()) + Utils.white(vo.getDocNo()));
                } else {
                    draftVo.setDocumentInfo("随附单证" + i + ":" + Utils.white(vo.getDocTypeDesc()) + Utils.white(vo.getDocNo()));
                }
                i++;
            }
        }

        draftVo.setBusinessMatter(Utils.space(tdDetail.getBusinessMatter()));
        draftVo.setBusinessMatter(Utils.space(draftVo.getBusinessMatterDesc())); // 业务类型-含有自报自缴类型


        if (StringUtil.isNotBlank(tdDetail.getComment())) {
            draftVo.setComment("  备注：" + tdDetail.getComment()); // 备注
        }
        if (StringUtil.isNotBlank(tdDetail.getComment())) {
            draftVo.setMarkNo("备注：" + tdDetail.getComment()); // 备注
        }
        if (StringUtil.isNotBlank(tdDetail.getMarkNo())) {
            draftVo.setComment("  " + tdDetail.getMarkNo()); // 标记唛码
        }
        if (StringUtil.isNotBlank(tdDetail.getRelRecord())) {
            draftVo.setComment(Utils.white(draftVo.getComment()) + "  关联备案：" + tdDetail.getRelRecord()); // 标记唛码 + 关联备案
        }
        draftVo.setName(Utils.space(null)); // 申报人员
        draftVo.setUserNo(Utils.space(null)); // 申报人员编号
        draftVo.setTel(Utils.space(null)); // 电话
        tdPartner = this.partnerDao.findByHeadIdAndType(id, "R");
        if (tdPartner != null) {
            draftVo.setSocialCreditCode4(Utils.space(tdPartner.getSocialCreditCode())); // 申报单位-社会信用代码
            draftVo.setPartnerName4(Utils.space(WordUtils.reword(tdPartner.getPartnerName()))); // 申报单位-名称
        }

        return draftVo;
    }

    @Override
    public List<ItemCompareVo> getDraftItems(Long id, Long taskId) {
        /**
         * <p>Field list: 表格list</p>
         */
        // 报关单表体
        List<TdHsItem> tdHsItems = this.hsItemDao.findByHeadIdOrderByItemNoAsc(id);
        // ocr数据表体
        List<ItemCompareVo> ocrItems = this.ocrHsItemDao.getItemListByDeclId(id);
        List<ItemCompareVo> list = new ArrayList<>();
        List<ItemCompareVo> itemCompareVos = new ArrayList<>();
        if (Utils.isNotEmpty(tdHsItems)) {
            for (TdHsItem tdHsItem : tdHsItems) {
                ItemCompareVo previewVos = new ItemCompareVo();
                TdHsItemSupplement tdHsItemSupplement = null;
                if (tdHsItem.getId() != null && tdHsItem.getHeadId() != null) {
                    tdHsItemSupplement = this.hsItemSupplementDao.findByItemId(tdHsItem.getId());
                }
                if (tdHsItemSupplement != null) {
                    Integer itemNo = tdHsItem.getItemNo();
                    if (null != itemNo) {
                        previewVos.setItemNo(Utils.space(itemNo.toString())); // 项号
                    } else {
                        previewVos.setItemNo(Utils.space(null));
                    }
                    if (null != tdHsItem.getHsCode()) {
                        previewVos.setHsCode(Utils.space(tdHsItem.getHsCode())); // 商品编号
                    } else {
                        previewVos.setHsCode(Utils.space(null));
                    }
                    if (null != tdHsItem.getHsName()) {
                        previewVos.setHsName(Utils.space(tdHsItem.getHsName())); // 商品名称
                    } else {
                        previewVos.setHsName(Utils.space(null));
                    }
                    List<String> illegalCharacter = new ArrayList<>(); //xml非法字符
                    illegalCharacter.add("&");
                    illegalCharacter.add("'");
                    illegalCharacter.add("\"");
                    illegalCharacter.add(">");
                    illegalCharacter.add("<");
                    if (null != tdHsItem.getId()) {
                        previewVos.setItemSpecModel(Utils.space(Utils.removeSplitter(this.itemAttributeDao.getAttributeByItemId(tdHsItem.getId())))); // 规格型号
                    } else {
                        previewVos.setItemSpecModel(Utils.space(null));
                    }
//                    previewVos.setItemSpecModel(Utils.space(this.itemAttributeDao.getAttributeByItemId(tdHsItem.getId()))); // 规格型号
                    // 非法字符串替换
                    for (String word : illegalCharacter) {
                        if (StringUtil.isNotBlank(previewVos.getItemSpecModel())) {
                            if (previewVos.getItemSpecModel().contains(word)) {
                                previewVos.setItemSpecModel(WordUtils.reword(previewVos.getItemSpecModel()));
                            } else {
                                previewVos.setItemSpecModel(Utils.white(previewVos.getItemSpecModel()));
                            }
                        } else {
                            previewVos.setItemSpecModel(Utils.white(previewVos.getItemSpecModel()));
                        }
                    }
                    String quantity = null;
                    if (null != tdHsItem.getQuantity()) {
                        quantity = tdHsItem.getQuantity().stripTrailingZeros().toPlainString(); // 计量单位数量1
                    }
                    previewVos.setUnitQty(Utils.space(quantity));
                    String unitDesc = null;
                    if (StringUtil.isNotBlank(tdHsItem.getDeclareUnit())) {
                        unitDesc = DictUtils.getValue(Constants.DictType.P_MEASUREMENT_UNIT, tdHsItem.getDeclareUnit());
                    }
                    previewVos.setUnitDesc(Utils.space(unitDesc)); // 成交单位名称
                    String unitQty = null;
                    if (null != tdHsItem.getUnitQty()) {
                        unitQty = tdHsItem.getUnitQty().stripTrailingZeros().toPlainString(); // 计量单位数量1
                    }
                    previewVos.setUnit1Qty(Utils.space(unitQty));
                    String unit1Desc = null;
                    if (StringUtil.isNotBlank(tdHsItem.getUnit())) {
                        unit1Desc = DictUtils.getValue(Constants.DictType.P_MEASUREMENT_UNIT, tdHsItem.getUnit());
                    }
                    previewVos.setUnit1Desc(unit1Desc);
                    String unitPice = null;
                    if (null != tdHsItem.getUnitPrice()) {
                        unitPice = tdHsItem.getUnitPrice().stripTrailingZeros().toPlainString(); // 单价
                    }
                    previewVos.setUnitPrice(Utils.space(unitPice));
                    String amount = null;
                    if (null != tdHsItem.getCurrencyAmount()) {
                        amount = tdHsItem.getCurrencyAmount().stripTrailingZeros().toPlainString(); // 总价
                    }
                    previewVos.setCurrencyAmount(Utils.space(amount));
                    String currencyCodeDesc = null;
                    if (StringUtil.isNotBlank(tdHsItem.getCurrencyCode())) {
                        currencyCodeDesc = DictUtils.getValue(Constants.DictType.P_CURRENCY_CODE, tdHsItem.getCurrencyCode());
                    }
                    previewVos.setCurrencyCodeDesc(Utils.space(currencyCodeDesc)); // 币制
                    String originCountry = null;
                    if (StringUtil.isNotBlank(tdHsItem.getOriginCountry())) {
                        originCountry = this.tpCountryReginDao.findByCode(tdHsItem.getOriginCountry()).getName();
                    }
                    previewVos.setOriginCountry(Utils.space(originCountry)); // 原产国
                    String destinationCountry = null;
                    if (StringUtil.isNotBlank(tdHsItem.getDestinationCountry())) {
                        destinationCountry = this.tpCountryReginDao.findByCode(tdHsItem.getDestinationCountry()).getName();
                    }
                    previewVos.setDestinationCountry(Utils.space(destinationCountry)); // 最终目的国
                    String domesticDestCodeDesc = null;
                    if (StringUtil.isNotBlank(tdHsItemSupplement.getDomesticDestCode())) {
                        TpDomesticArea tpDomesticArea = this.tpDomesticAreaDao.findByCode(tdHsItemSupplement.getDomesticDestCode());
                        if (StringUtil.isNotBlank(tdHsItemSupplement.getDestCode())) {
                            TpAdministrativeDivision dest = this.tpAdministrativeDivisionDao.findByCode(tdHsItemSupplement.getDestCode());
                            domesticDestCodeDesc = "(" + tdHsItemSupplement.getDomesticDestCode() + "/" + tdHsItemSupplement.getDestCode() + ")" + tpDomesticArea.getName() + "/" + dest.getName();
                        } else {
                            domesticDestCodeDesc = "(" + tdHsItemSupplement.getDomesticDestCode() + ")" + tpDomesticArea.getName();
                        }
                    } else {
                        if (StringUtil.isNotBlank(tdHsItemSupplement.getDestCode())) {
                            TpAdministrativeDivision dest = this.tpAdministrativeDivisionDao.findByCode(tdHsItemSupplement.getDestCode());
                            domesticDestCodeDesc = "(" + tdHsItemSupplement.getDestCode() + ")" + dest.getName();
                        }
                    }
                    previewVos.setDomesticDestCodeDesc(Utils.space(domesticDestCodeDesc)); // 境内目的地
                    String taxFreeMethodDesc = null;
                    if (StringUtil.isNotBlank(tdHsItem.getTaxFreeMethod())) {
                        taxFreeMethodDesc = DictUtils.getValue(Constants.DictType.P_TAX_REDUCTION_EXEMPTION, tdHsItem.getTaxFreeMethod());
                    }
                    previewVos.setTaxFreeMethodDesc(Utils.space(taxFreeMethodDesc)); // 征免方式

                } else {
                    previewVos.setItemNo(Utils.space(null));
                    previewVos.setHsCode(Utils.space(null));
                    previewVos.setHsName(Utils.space(null));
                    previewVos.setItemSpecModel(Utils.space(null));
                    previewVos.setUnitQty(Utils.space(null));
                    previewVos.setUnitDesc(Utils.space(null));
                    previewVos.setUnitPrice(Utils.space(null));
                    previewVos.setCurrencyAmount(Utils.space(null));
                    previewVos.setCurrencyCodeDesc(Utils.space(null));
                    previewVos.setOriginCountry(Utils.space(null));
                    previewVos.setDepartureCountry(Utils.space(null));
                    previewVos.setDestinationCountry(Utils.space(null));
                    previewVos.setDomesticDestCodeDesc(Utils.space(null));
                    previewVos.setTaxFreeMethodDesc(Utils.space(null));
                }
                list.add(previewVos);
            }
            itemCompareVos = this.compareObject(list, ocrItems);
        }

        return itemCompareVos;
    }

    private List<ItemCompareVo> compareObject(List<ItemCompareVo> hsItemList, List<ItemCompareVo> ocrList) {
        for (ItemCompareVo v1 : hsItemList) {
            for (ItemCompareVo v2 : ocrList) {
                if (v1.getItemNo().equals(v2.getItemNo())) {
                    // 属性值比较
                    if (!v1.getHsCode().equals(v2.getHsCode())) {
                        v1.setB(Constants.YES);
                        v1.setHscode2(Utils.space(v2.getHsCode()));
                    }
                    if (!v1.getHsName().equals(v2.getHsName())) {
                        v1.setB2(Constants.YES);
                        v1.setHsName2(Utils.space(v2.getHsName()));
                        v1.setItemSpecModel2(Utils.space(v2.getItemSpecModel()));
                    }
                    if (!v1.getItemSpecModel().equals(v2.getItemSpecModel())) {
                        v1.setB3(Constants.YES);
                        v1.setHsName2(Utils.space(v2.getHsName()));
                        v1.setItemSpecModel2(Utils.space(v2.getItemSpecModel()));
                    }
                    if (!v1.getUnitQty().equals(v2.getUnitQty())) {
                        v1.setB4(Constants.YES);
                        v1.setUnitQty2(Utils.space(v2.getUnitQty()));
                        v1.setUnitDesc2(Utils.space(v2.getUnitDesc()));
                    }
                    if (!v1.getUnitDesc().equals(v2.getUnitDesc())) {
                        v1.setB5(Constants.YES);
                        v1.setUnitQty2(Utils.space(v2.getUnitQty()));
                        v1.setUnitDesc2(Utils.space(v2.getUnitDesc()));
                    }
                    if (!v1.getUnitPrice().equals(v2.getUnitPrice())) {
                        v1.setB6(Constants.YES);
                        v1.setUnitPrice2(Utils.space(v2.getUnitPrice()));
                        v1.setCurrencyAmount2(Utils.space(v2.getCurrencyAmount()));
                        v1.setCurrencyCodeDesc2(Utils.space(v2.getCurrencyCodeDesc()));
                    }
                    if (!v1.getCurrencyAmount().equals(v2.getCurrencyAmount())) {
                        v1.setB7(Constants.YES);
                        v1.setUnitPrice2(Utils.space(v2.getUnitPrice()));
                        v1.setCurrencyAmount2(Utils.space(v2.getCurrencyAmount()));
                        v1.setCurrencyCodeDesc2(Utils.space(v2.getCurrencyCodeDesc()));
                    }
                    if (!v1.getCurrencyCodeDesc().equals(v2.getCurrencyCodeDesc())) {
                        v1.setB8(Constants.YES);
                        v1.setUnitPrice2(Utils.space(v2.getUnitPrice()));
                        v1.setCurrencyAmount2(Utils.space(v2.getCurrencyAmount()));
                        v1.setCurrencyCodeDesc2(Utils.space(v2.getCurrencyCodeDesc()));
                    }
                    if (!v1.getOriginCountry().equals(v2.getOriginCountry())) {
                        v1.setB9(Constants.YES);
                        v1.setOriginCountry2(Utils.space(v2.getOriginCountry()));
                    }

                    if (!v1.getDestinationCountry().equals(v2.getDestinationCountry())) {
                        v1.setB11(Constants.YES);
                        v1.setDestinationCountry2(Utils.space(v2.getDestinationCountry()));
                    }
                    if (!v1.getDomesticDestCodeDesc().equals(v2.getDomesticDestCodeDesc())) {
                        v1.setB12(Constants.YES);
                        v1.setDomesticDestCodeDesc2(Utils.space(v2.getDomesticDestCodeDesc()));
                    }
                    if (!v1.getTaxFreeMethodDesc().equals(v2.getTaxFreeMethodDesc())) {
                        v1.setB13(Constants.YES);
                        v1.setTaxFreeMethodDesc2(Utils.space(v2.getTaxFreeMethodDesc()));
                    }
                    if (!v1.getUnit1Qty().equals(v2.getUnit1Qty())) {
                        v1.setB14(Constants.YES);
                        v1.setUnit1Qty2(Utils.space(v2.getUnit1Qty()));
                        v1.setUnit1Desc2(Utils.space(v2.getUnit1Desc()));
                    }
                    if (!v1.getUnit1Desc().equals(v2.getUnit1Desc())) {
                        v1.setB15(Constants.YES);
                        v1.setUnit1Qty2(Utils.space(v2.getUnit1Qty()));
                        v1.setUnit1Desc2(Utils.space(v2.getUnit1Desc()));
                    }
                    v1.setSpecialRelationDesc(Utils.space(v2.getSpecialRelationDesc()));
                    v1.setPriceImpactDesc(Utils.space(v2.getPriceImpactDesc()));
                    v1.setRoyaltyPaymentDesc(Utils.space(v2.getRoyaltyPaymentDesc()));
                }
            }
        }
        return hsItemList;
    }

    @Override
    public ToDocument getPdfView(Long id) {
        return this.toDocumentDao.getLastDocumentByTaskIdAndType(id, Constants.DICT_CODE_DOC_TYPE_BGJYG);
    }

    @Override
    @Transactional
    public String wmsSave(WmsDataVo vo, LoginUser user) {
        TdDeclarationTask declarationTask = this.declarationTaskDao.get(vo.getId());
        if (null != declarationTask) {
            Date now = new Date();
            declarationTask.setWmsNumber(vo.getWmsNumber());
            // 根据业务类型判断出入库日期
            Boolean flag = false;
            if (Constants.BusinessType.IMPORT.equals(DeclUtils.getIeType(declarationTask.getBusinessType()))) {
                flag = true;
            }
            declarationTask.setStatus(Constants.Dtask.STATUS_WMS_OUT_IN);
            declarationTask.update(user, now);
            this.declarationTaskDao.save(declarationTask);
            // 通关任务操作记录
            TdDeclarationTaskStatus status = new TdDeclarationTaskStatus();
            status.setStatus(Constants.Dtask.STATUS_WMS_OUT_IN);
            status.setOperation(Constants.Dtask.STATUS_WMS_UPDATE);
            status.setTaskId(declarationTask.getId());
            status.setIsRead(Constants.NO);
            status.setMsg(vo.getWmsNumber() + Constants.SLASH_WITH_SPACE + DateUtils.fmtDate(vo.getWmsDate()));
            DeclUtils.setTaskStatusCompany(status, user);
            status.init(user, now);
            this.declarationTaskStatusDao.save(status);
            // 判断是否有KPI
            TbKpiData kpiData = this.tbKpiDataDao.findByTaskId(vo.getId());
            if (null != kpiData) {
                if (flag) {
                    kpiData.setDoi(vo.getWmsDate());
                } else {
                    kpiData.setWmsDate(vo.getWmsDate());
                }
                kpiData.setWmsNumber(vo.getWmsNumber());
                kpiData.update(user, now);
                this.tbKpiDataDao.save(kpiData);
                this.createKpiResult(kpiData, user, false);
            }
            // 当进口自贸核注清单所属的任务内WMS入库日期输入后，进口自贸清单所有的表体明细增加到明细表内
            ToHead head = this.toHeadDao.get(declarationTask.getListId());
            // IQD标识为空时提示
            if(null != head && DeclUtils.validateBusinessType(declarationTask.getBusinessType())) {
                if (StringUtil.isNotBlank(head.getIqdMark())) {
                    if ((Constants.BusinessType.CHECKLIST_IMPORT.equals(declarationTask.getBusinessType()) || Constants.BusinessType.CHECKLIST_FTA_IMPORT.equals(declarationTask.getBusinessType())) && null != vo.getWmsDate()) {
                        // 核注清单明细
                        ToDetail toDetail = this.toDetailDao.findByHeadId(declarationTask.getListId());
                        // 当IQD标识为C，同时核注清单为自贸进口核注清单时,明细数据增加记录实际数量等于法一数量及单位
                        if (null != head && Constants.InvMark.INV_MARK_SUB.equals(head.getIqdMark())) {
                            List<ToItem> itemList = this.toItemDao.findByHeadId(declarationTask.getListId());
                            for (ToItem item : itemList) {
                                TrBondedInvDetail detail = this.bondedInvDetailDao.findByItemId(item.getId());
                                if (null != detail && null != detail.getId()) {
                                    detail.setCheckListNo(toDetail.getChecklistNo());
                                    detail.setActualRemainQuantity(item.getUnitQty());
                                    detail.setActualRemainUnit(item.getUnit());
                                    detail.setWmsNo(vo.getWmsNumber());
                                    detail.setUpdateBy(user.getUserNo());
                                    detail.setUpdateTime(new Date());
                                    bondedInvDetailDao.save(detail);
                                    // 库存表更新
                                    this.updateBondedInventory(declarationTask.getId(), item, false, user);
                                } else {
                                    // TODO: 邮件发送提醒：没有找到对应的item
                                    // 自贸出口核注，计入保税仓库-进出明细表
                                    this.saveBondedInvDetail(declarationTask, item, user, vo.getWmsNumber());
                                    // 库存表更新
                                    this.updateBondedInventory(declarationTask.getId(), item, false, user);
                                }
                            }
                        } else {
                            List<ToItem> itemList = this.toItemDao.findByHeadId(declarationTask.getListId());
                            for (ToItem item : itemList) {
                                item.setIntoProcuctNumber(Utils.white(item.getItemNo()));
                                item.setCheckReleaseIntoNumber(toDetail.getChecklistNo());
                                this.toItemDao.save(item);
                                this.saveBondedInvDetail(declarationTask, item, user, vo.getWmsNumber());
                                // 库存表更新
                                this.updateBondedInventory(declarationTask.getId(), item, true, user);
                            }
                        }
                    }
                    // 当出口核注清单所属的任务内WMS出库日期输入后，明细数据增加记录实际数量等于法一数量及单位
                    // 说明：自贸如果加进来，则改成
                    // if ((Constants.BusinessType.CHECKLIST_EXPORT.equals(declarationTask.getBusinessType()) || Constants.BusinessType.CHECKLIST_FTA_EXPORT.equals(declarationTask.getBusinessType())) && null != vo.getWmsDate())
                    if ((Constants.BusinessType.CHECKLIST_EXPORT.equals(declarationTask.getBusinessType()) || Constants.BusinessType.CHECKLIST_FTA_EXPORT.equals(declarationTask.getBusinessType())) && null != vo.getWmsDate()) {
                        // 核注清单明细
                        ToDetail toDetail = this.toDetailDao.findByHeadId(declarationTask.getListId());
                        // 当IQD标识为C，同时核注清单为自贸进口核注清单时,明细数据增加记录实际数量等于法一数量及单位
                        if (null != head && Constants.InvMark.INV_MARK_SUB.equals(head.getIqdMark())) {
                            List<ToItem> itemList = this.toItemDao.findByHeadId(declarationTask.getListId());
                            for (ToItem item : itemList) {
                                TrBondedInvDetail detail = this.bondedInvDetailDao.findByItemId(item.getId());
                                if (null != detail && null != detail.getId()) {
                                    detail.setCheckListNo(toDetail.getChecklistNo());
                                    detail.setActualRemainQuantity(item.getUnitQty());
                                    detail.setActualRemainUnit(item.getUnit());
                                    detail.setWmsNo(vo.getWmsNumber());
                                    detail.setUpdateBy(user.getUserNo());
                                    detail.setUpdateTime(new Date());
                                    bondedInvDetailDao.save(detail);
                                    // 库存表更新
                                    this.updateBondedInventory(declarationTask.getId(), item, false, user);
                                } else {
                                    // TODO: 邮件发送提醒：没有找到对应的item
                                    // 自贸出口核注，计入保税仓库-进出明细表
                                    this.saveBondedInvDetail(declarationTask, item, user, vo.getWmsNumber());
                                    // 库存表更新
                                    this.updateBondedInventory(declarationTask.getId(), item, false, user);
                                }
                            }
                        } else {
                            List<ToItem> itemList = this.toItemDao.findByHeadId(declarationTask.getListId());
                            for (ToItem item : itemList) {
                                item.setIntoProcuctNumber(Utils.white(item.getItemNo()));
                                item.setCheckReleaseIntoNumber(toDetail.getChecklistNo());
                                this.toItemDao.save(item);
                                this.saveBondedInvDetail(declarationTask, item, user, vo.getWmsNumber());
                                // 库存表更新
                                this.updateBondedInventory(declarationTask.getId(), item, true, user);
                            }
                        }
                    }
                } else {
                    return OpResult.IQD_MARK_IS_NOT_EXIST.getCode();
                }
            }
        }
        return null;
    }

    /**
     * <p>Description: 计入保税仓库-进出明细表</p>
     */
    private void saveBondedInvDetail(TdDeclarationTask task, ToItem item, LoginUser user, String wmsNumber) {
        // 核注清单表头
        ToHead head = this.toHeadDao.get(item.getHeadId());
        // 核注清单明细
        ToDetail toDetail = this.toDetailDao.findByHeadId(item.getHeadId());
        // 工厂
        ToPartner company = this.toPartnerDao.findByHeadIdAndType(item.getHeadId(), Constants.Partner.CUSVBS);
        // 报关单明细
        TdDetail tdDetail = null;
        if (null != task && null != task.getDeclId()) {
            tdDetail = this.detailDao.findByHeadId(task.getDeclId());
        }
        // 库存表
        TrBondedInventory bondedInventory = this.bondedInventoryDao.get(item.getBondedInvId());
        // 进出明细表
        TrBondedInvDetail detail = this.bondedInvDetailDao.findByItemId(item.getId());
        TrBondedInvDetail bondedInvDetail = new TrBondedInvDetail();
        bondedInvDetail.setItemId(item.getId());
        bondedInvDetail.setIeType(task.getIeType());
        bondedInvDetail.setToHeadId(task.getListId());
        bondedInvDetail.setCheckListNo(toDetail.getChecklistNo());
        bondedInvDetail.setOutboundOrderId(item.getOutboundId());
        bondedInvDetail.setCheckListitemNo(item.getItemNo());
        if (null != tdDetail && null != tdDetail.getBillNo()) {
            bondedInvDetail.setBillNo(tdDetail.getBillNo());
        }
        bondedInvDetail.setTdHeadId(task.getDeclId());
        bondedInvDetail.setEntryId(head.getEntryId());
        bondedInvDetail.setPartNum(item.getProductNo());
        bondedInvDetail.setPartDescCn(item.getProductName());
        bondedInvDetail.setPartDescEn(item.getDeclGoodsEname());
        bondedInvDetail.setHsCode(item.getHsCode());
        bondedInvDetail.setBatchNo(item.getBatchNo());
        bondedInvDetail.setOriginCountry(item.getOriginCountry());
        bondedInvDetail.setDeclQuantity(new BigDecimal(item.getQuantity().toString()));
        bondedInvDetail.setDeclUnit(item.getDeclareUnit());
        bondedInvDetail.setUnitQuantity1(item.getUnitQty());
        bondedInvDetail.setUnit1(item.getUnit());
        bondedInvDetail.setNetWeight(item.getNetWeight());
        bondedInvDetail.setGrossWeight(item.getGrossWeight());
        bondedInvDetail.setUnitPrice(item.getUnitPrice());
        bondedInvDetail.setTotalPrice(item.getCurrencyAmount());
        bondedInvDetail.setCurrencyCode(item.getCurrencyCode());
        bondedInvDetail.setRemainQuantity(item.getUnitQty());
        bondedInvDetail.setRemainUnit(item.getUnit());
        bondedInvDetail.setActualRemainQuantity(item.getUnitQty());
        bondedInvDetail.setActualRemainUnit(item.getUnit());
        bondedInvDetail.setTaskId(task.getId());
        if (null != bondedInventory) {
            bondedInvDetail.setIqd(bondedInventory.getIqd());
            bondedInvDetail.setIqdNo(Integer.valueOf(bondedInventory.getIqdNo()));
            bondedInvDetail.setPo(bondedInventory.getPo());
            bondedInvDetail.setDn(bondedInventory.getDn());
        } else {
            bondedInvDetail.setIqd(toDetail.getChecklistNo());
            bondedInvDetail.setIqdNo(item.getItemNo());
            if (StringUtil.isBlank(item.getIntoPoNumber())) {
                bondedInvDetail.setPo(this.toBusdocRefDao.getpoNumber(item.getHeadId()));
            } else {
                bondedInvDetail.setPo(item.getIntoPoNumber());
            }
            if (StringUtil.isBlank(item.getIntoDnNumber())) {
                bondedInvDetail.setDn(this.toBusdocRefDao.getdnNumber(item.getHeadId()));
            } else {
                bondedInvDetail.setDn(item.getIntoDnNumber());
            }
        }
        bondedInvDetail.setWmsNo(wmsNumber);
        if (null != company) {
            bondedInvDetail.setFactoryCode(company.getInternalId());
        }
        bondedInvDetail.setDeleteMark(Constants.NO);
        bondedInvDetail.setUpdateBy(user.getUserNo());
        bondedInvDetail.setUpdateTime(new Date());
        if (null != detail && null != detail.getId()) {
            bondedInvDetail.setId(detail.getId());
        } else {
            bondedInvDetail.setCompanyId(head.getOwnerId());
            bondedInvDetail.setDeptId(head.getDeptId());
            bondedInvDetail.setCreateBy(user.getUserNo());
            bondedInvDetail.setCreateTime(new Date());
        }
        // 根据清单进出口标识ieType+任务表中的贸易方式tradeMode，检查字典项IQD标识是否存在
        if (null != head.getIqdMark()) {
            bondedInvDetail.setIqdMark(head.getIqdMark());
        }
        String checkResult = this.dictDao.getValueByTypeIdAndCode(Constants.DictType.INV_IQD, head.getIeType() + task.getTradeMode());
        if (null != checkResult) {
            bondedInvDetail.setIqdMark(checkResult);
        }
        bondedInvDetail.setBusinessType(task.getTradeMode());
        bondedInvDetail.setHandbookNo(toDetail.getHandbookNo()); // 手账册号
        bondedInvDetail.setCifPrice(item.getCifPrice()); // CIF单价
        ToTransportation trans = this.toTransportationDao.findByHeadId(item.getHeadId());
        if (null != trans) {
            bondedInvDetail.setTransMode(trans.getTransMode()); // 成交方式
        }
        bondedInvDetailDao.save(bondedInvDetail);
    }

    /**
     * <p>Description: 库存表更新</p>
     */
    private void updateBondedInventory(Long taskId, ToItem item, Boolean flag, LoginUser user) {
        // 是否有库存
        if (null != item.getBondedInvId()) {
            TrBondedInventory bondedInventory = this.bondedInventoryDao.get(item.getBondedInvId());
            // 根据对应库存IQD编号”、“IQD序号”、“物料号”、“批次号
            BondedInvQuery bondedInvQuery = new BondedInvQuery();
            bondedInvQuery.setIqd(bondedInventory.getIqd());
            bondedInvQuery.setIqdNo(Utils.parseInt(bondedInventory.getIqdNo()));
            bondedInvQuery.setPartNum(bondedInventory.getPartNum());
            bondedInvQuery.setBatchNo(bondedInventory.getBatchNo());
            List<BondedInvDetailVo> bondedInvDetailVos = this.bondedInvDetailDao.searchBondedInvDetailListGroupByType(bondedInvQuery);
            Map<String, BigDecimal> decimalMap = this.calculateQuantity(bondedInvDetailVos);
            if (Utils.isNotEmpty(decimalMap)) {
                if (null != bondedInventory.getOriginRemainQuantity()) {
                    bondedInventory.setRemainQuantity(bondedInventory.getOriginRemainQuantity().add(decimalMap.get("qty1")));
                }
                if (null != bondedInventory.getOriginActualRemainQuantity()) {
                    bondedInventory.setActualRemainQuantity(bondedInventory.getOriginActualRemainQuantity().add(decimalMap.get("qty2")));
                }
                bondedInventory.update(user, new Date());
                bondedInventory.setUpdateTime(new Date());
                this.bondedInventoryDao.save(bondedInventory);
            } else {
                bondedInventory.setRemainQuantity(bondedInventory.getOriginRemainQuantity());
                bondedInventory.setActualRemainQuantity(bondedInventory.getOriginActualRemainQuantity());
                bondedInventory.setUpdateBy(user.getUserNo());
                bondedInventory.setUpdateTime(new Date());
                this.bondedInventoryDao.save(bondedInventory);
            }
        } else {
            // 如果是进境添加库存
            if (flag) {
                ToDetail toDetail = this.toDetailDao.findByHeadId(item.getHeadId());
                TrBondedInventory trBondedInventory = new TrBondedInventory();
                TrBondedInventory inventory = null;
                if (StringUtil.isNotBlank(toDetail.getChecklistNo())) {
                    inventory = this.bondedInventoryDao.findByItemId(item.getId());
                    trBondedInventory.setItemId(item.getId());
                    if (StringUtil.isBlank(item.getIntoPoNumber())) {
                        trBondedInventory.setPo(this.toBusdocRefDao.getpoNumber(item.getHeadId()));
                    } else {
                        trBondedInventory.setPo(item.getIntoPoNumber());
                    }
                    if (StringUtil.isBlank(item.getIntoDnNumber())) {
                        trBondedInventory.setDn(this.toBusdocRefDao.getdnNumber(item.getHeadId()));
                    } else {
                        trBondedInventory.setDn(item.getIntoDnNumber());
                    }
                    trBondedInventory.setIqd(toDetail.getChecklistNo());
                    trBondedInventory.setIqdNo(Utils.toString(item.getItemNo()));
                    trBondedInventory.setPartNum(item.getProductNo());
                    trBondedInventory.setPartDescCn(item.getProductName());
                    trBondedInventory.setPartDescEn(item.getDeclGoodsEname());
                    trBondedInventory.setBatchNo(item.getBatchNo());
                    trBondedInventory.setOriginCountry(item.getOriginCountry());
                    trBondedInventory.setDeclQuantity(Utils.parseBigDecimal(item.getQuantity()));
                    trBondedInventory.setDeclUnit(item.getDeclareUnit());
                    trBondedInventory.setUnitQuantity1(item.getUnitQty());
                    trBondedInventory.setUnit1(item.getUnit());
                    trBondedInventory.setNetWeight(item.getNetWeight());
                    trBondedInventory.setUnitPrice(item.getUnitPrice());
                    trBondedInventory.setTotalPrice(item.getCurrencyAmount());
                    trBondedInventory.setCurrencyCode(item.getCurrencyCode());
                    trBondedInventory.setTaskId(taskId);
                    trBondedInventory.setRemainQuantity(item.getUnitQty());
                    trBondedInventory.setOriginRemainQuantity(item.getUnitQty());
                    trBondedInventory.setActualRemainQuantity(item.getUnitQty());
                    trBondedInventory.setOriginActualRemainQuantity(item.getUnitQty());
                    if (null != item.getIntoUnitPrice()) {
                        trBondedInventory.setUnitPrice1(item.getIntoUnitPrice());
                    } else {
                        if (item.getUnitQty() != null && item.getCurrencyAmount() != null && BigDecimal.ZERO.compareTo(item.getUnitQty()) != 0) {
                            trBondedInventory.setUnitPrice1(item.getCurrencyAmount().divide(item.getUnitQty(), 6, BigDecimal.ROUND_HALF_UP));
                        }
                    }
                    trBondedInventory.setHandbookNo(toDetail.getHandbookNo());
                    trBondedInventory.setHsCode(item.getHsCode());
                    trBondedInventory.setHsName(item.getHsName());
                    trBondedInventory.setGrossWeight(item.getGrossWeight());
                    trBondedInventory.setCifPrice(item.getCifPrice());
                    ToTransportation trans = this.toTransportationDao.findByHeadId(item.getHeadId());
                    if (null != trans) {
                        trBondedInventory.setTransMode(trans.getTransMode());
                    }
                    trBondedInventory.setDeleteMark(Constants.NO);
                    if (null != inventory && null != inventory.getId()) {
                        trBondedInventory.setId(inventory.getId());
                    } else {
                        trBondedInventory.setCreateBy(user.getUserNo());
                        trBondedInventory.setCreateTime(new Date());

                        ToHead toHead = this.toHeadDao.get(item.getHeadId());
                        trBondedInventory.setCompanyId(toHead.getOwnerId());
                        trBondedInventory.setDeptId(toHead.getDeptId());
                    }
                    trBondedInventory.setUpdateTime(new Date());
                    trBondedInventory.setUpdateBy(user.getUserNo());
                    this.bondedInventoryDao.save(trBondedInventory);
                }
            } else {
                // TODO: 邮件发送提醒：库存为负
                // 邮件通知系统管理员库存为负
            }
        }

    }

    /**
     * <p>Description: 计算库存明细预留数量，实际数量</p>
     *
     * @param list 库存明细
     * @return 结果
     */
    private Map<String, BigDecimal> calculateQuantity(List<BondedInvDetailVo> list) {
        Map<String, BigDecimal> map = new HashMap<>();
        // 预留数量
        BigDecimal qty1 = new BigDecimal(0);
        // 实际数量
        BigDecimal qty2 = new BigDecimal(0);
        for (BondedInvDetailVo vo : list) {
            if (Constants.InvMark.INV_MARK_ADD.equals(vo.getIqdMark())) {
                if (!Constants.InvIeMark.INV_TYPE_I.equals(vo.getIeType())) {
                    if (null != vo.getRemainQuantity()) {
                        qty1 = qty1.add(vo.getRemainQuantity());
                    }
                    if (null != vo.getActualRemainQuantity()) {
                        qty2 = qty2.add(vo.getActualRemainQuantity());
                    }
                }
            } else if (Constants.InvMark.INV_MARK_SUB.equals(vo.getIqdMark())) {
                if (null != vo.getRemainQuantity()) {
                    qty1 = qty1.subtract(vo.getRemainQuantity());
                }
                if (null != vo.getActualRemainQuantity()) {
                    qty2 = qty2.subtract(vo.getActualRemainQuantity());
                }
            }
        }
        map.put("qty1", qty1);
        map.put("qty2", qty2);
        return map;
    }

    @Transactional
    @Override
    public String switchPakgeUnit(QueryVo qv, LoginUser loginUser) {
        TdHead head = this.headDao.get(qv.getId());
        if (Utils.isNotNull(head)) {
            if (null != head.getOwnerId() && null != head.getDeptId()) {
                List<TdHsItem> itemList = this.hsItemDao.findByHeadId(head.getId());
                Date now = new Date();
                List<String> list = new ArrayList<>();
                if (Utils.isNotEmpty(itemList)) {
                    for (TdHsItem item : itemList) {
                        StringBuilder str = new StringBuilder();
                        TmPart part = this.partDao.findAllByCompanyAndDeptAndPartNum(item.getProductNo(), head.getOwnerId(), head.getDeptId());
                        part = DeptPartUtil.switchPart(part, item.getProductNo(), head.getOwnerId(), head.getDeptId());
                        if (null != part) {
                            // 获取包装维护数据
                            TmPartPackageMaintain tmPartPackageMaintain = this.tmPartPackageMaintainDao.findByItemIdAndActive(part.getId(), Constants.YES);
                            if (null != tmPartPackageMaintain) {
                                if (null != item.getDeclareUnit() && null != tmPartPackageMaintain.getUnit()) {
                                    // 比较申报单位是否一致
                                    if (item.getDeclareUnit().equals(tmPartPackageMaintain.getPkgType())) {
                                        if (StringUtil.isBlank(tmPartPackageMaintain.getPkgType())) {
                                            str.append(item.getItemNo() + ",包装维护申报单位为空;");
                                        }
                                        BigDecimal unitQuantity = null;
                                        // 物料的法定数量 = 申报数量 * 物料主数据法定数量 / 申报数量
                                        if (null != tmPartPackageMaintain.getPackages() && (null != tmPartPackageMaintain.getQuantity() && !BigDecimal.ZERO.equals(tmPartPackageMaintain.getQuantity()))) {
                                            unitQuantity = (Utils.parseBigDecimal(item.getQuantity()).multiply(tmPartPackageMaintain.getPackages()).divide(tmPartPackageMaintain.getQuantity(), 5, BigDecimal.ROUND_HALF_UP)).setScale(4, BigDecimal.ROUND_HALF_UP);
                                            item.setUnitQty(unitQuantity);
                                            item.setNetWeight(unitQuantity);
                                            this.hsItemDao.save(item);
                                        } else {
                                            str.append(item.getItemNo() + ",物料包装申报数量或法定数量为空;");
                                        }
                                    } else {
                                        str.append(item.getItemNo() + ",申报计量单位与包装维护申报单位不一致;");
                                    }
                                } else {
                                    str.append(item.getItemNo() + ",报关单商品成交单位或物料包装维护法定单位为空;");
                                }
                            } else {
                                item.setUnitQty(item.getQuantity());
                                item.setNetWeight(item.getQuantity());
                                this.hsItemDao.save(item);
                                str.append(item.getItemNo() + ",包装主数据未维护");
                            }
                        } else {
                            str.append(item.getItemNo() + ",物料主数据未维护;");
                        }
                        if (StringUtil.isNotBlank(str.toString())) {
                            list.add(str.toString());
                        }
                    }
                    if (Utils.isNotEmpty(list)) {
                        return Utils.join(list, Constants.CSV_RN);
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    /**
     * <p>Description: 创建/更新KPI结果</p>
     *
     * @param kd   KPI数据
     * @param user 用户
     * @param flag 是否是更新船期数据
     * @return 结果
     */
    private String createKpiResult(TbKpiData kd, LoginUser user, Boolean flag) {
        String tradeMode = this.declarationTaskDao.findTradeModeById(kd.getTaskId());
        if (null != tradeMode && Utils.contains(this.tkpi, tradeMode)) {
            Date now = new Date();
            List<TbKpiResult> list = new ArrayList<>();
            switch (tradeMode) {
                case "s1":
                    kd.setIdDate(kd.getDoi());
                    list.add(this.setValue(kd.getId(), user, now, kd.getDoi(), kd.getDondt(), "k1", "k11"));
                    break;
                case "s2":
                    kd.setIdDate(kd.getDoi());
                    if (Constants.TransportMode.AIR.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoi(), kd.getAnt(), "k1", "k123"));
                    } else if (Constants.TransportMode.FCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoi(), kd.getAta(), "k1", "k121"));
                    } else if (Constants.TransportMode.LCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoi(), kd.getAta(), "k1", "k122"));
                    }
                    break;
                case "s3":
                    kd.setIdDate(kd.getDoi());
                    if (Constants.TransportMode.AIR.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoi(), kd.getAnt(), "k1", "k133"));
                    } else if (Constants.TransportMode.FCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoi(), kd.getAta(), "k1", "k131"));
                    } else if (Constants.TransportMode.LCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoi(), kd.getAta(), "k1", "k132"));
                    }
                    break;
                case "s4":
                    kd.setIdDate(kd.getDoi());
                    if (Constants.TransportMode.AIR.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoi(), kd.getAnt(), "k1", "k143"));
                    } else if (Constants.TransportMode.FCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoi(), kd.getAta(), "k1", "k141"));
                    } else if (Constants.TransportMode.LCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoi(), kd.getAta(), "k1", "k142"));
                    }
                    break;
                case "s5":
                    kd.setIdDate(kd.getDodc());
                    list.add(this.setValue(kd.getId(), user, now, kd.getDodc(), kd.getDondt(), "k2", "k21"));
                    break;
                case "s6":
//                    kd.setIdDate(kd.getAta());
                    kd.setIdDate(kd.getEtd());
                    if (flag) {
                        kd.setIdDate(kd.getAtd());
                    }
                    if (Constants.TransportMode.AIR.equals(kd.getTransMode()) || Constants.TransportMode.LAND.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getEta(), kd.getAta(), "k3", "k311", "k312"));
                    } else if (Constants.TransportMode.SEA.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getEta(), kd.getAta(), "k3", "k313", "k314"));
                    }
                    list.add(this.setValue(kd.getId(), user, now, kd.getDofrb(), kd.getDonbt(), "k4", "k41"));
                    if (Constants.TransportMode.FCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoubol(), kd.getAtd(), "k5", "k511"));
                    } else if (Constants.TransportMode.LCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoubol(), kd.getAtd(), "k5", "k512"));
                    }
                    list.add(this.setValue(kd.getId(), user, now, kd.getDoubold(), kd.getEtd(), "k6", "k61"));
//                    list.add(this.setValue(kd.getId(), user, now, kd.getDocds(), kd.getEtd(), "k7", "k71"));
                    list.add(this.setValue(kd.getId(), user, now, kd.getDocds(), kd.getAtd(), "k7", "k71"));
                    break;
                case "s7":
//                    kd.setIdDate(kd.getAta());
                    kd.setIdDate(kd.getEtd());
                    if (flag) {
                        kd.setIdDate(kd.getAtd());
                    }
                    if (Constants.TransportMode.AIR.equals(kd.getTransMode()) || Constants.TransportMode.LAND.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getEta(), kd.getAta(), "k3", "k321", "k322"));
                    } else if (Constants.TransportMode.SEA.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getEta(), kd.getAta(), "k3", "k323", "k324"));
                    }
                    list.add(this.setValue(kd.getId(), user, now, kd.getDofrb(), kd.getDonbt(), "k4", "k42"));
                    if (Constants.TransportMode.FCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoubol(), kd.getAtd(), "k5", "k521"));
                    } else if (Constants.TransportMode.LCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoubol(), kd.getAtd(), "k5", "k522"));
                    }
                    list.add(this.setValue(kd.getId(), user, now, kd.getDoubold(), kd.getEtd(), "k6", "k62"));
//                    list.add(this.setValue(kd.getId(), user, now, kd.getDocds(), kd.getEtd(), "k7", "k72"));
                    list.add(this.setValue(kd.getId(), user, now, kd.getDocds(), kd.getAtd(), "k7", "k72"));
                    break;
                case "s8":
//                    kd.setIdDate(kd.getAta());
                    kd.setIdDate(kd.getEtd());
                    if (flag) {
                        kd.setIdDate(kd.getAtd());
                    }
                    if (Constants.TransportMode.AIR.equals(kd.getTransMode()) || Constants.TransportMode.LAND.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getEta(), kd.getAta(), "k3", "k331", "k332"));
                    } else if (Constants.TransportMode.SEA.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getEta(), kd.getAta(), "k3", "k333", "k334"));
                    }
                    list.add(this.setValue(kd.getId(), user, now, kd.getDofrb(), kd.getDonbt(), "k4", "k43"));
                    if (Constants.TransportMode.FCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoubol(), kd.getAtd(), "k5", "k531"));
                    } else if (Constants.TransportMode.LCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoubol(), kd.getAtd(), "k5", "k532"));
                    }
                    list.add(this.setValue(kd.getId(), user, now, kd.getDoubold(), kd.getEtd(), "k6", "k63"));
//                    list.add(this.setValue(kd.getId(), user, now, kd.getDocds(), kd.getEtd(), "k7", "k73"));
                    list.add(this.setValue(kd.getId(), user, now, kd.getDocds(), kd.getAtd(), "k7", "k73"));
                    break;
                case "s9":
//                    kd.setIdDate(kd.getAta());
                    kd.setIdDate(kd.getEtd());
                    if (flag) {
                        kd.setIdDate(kd.getAtd());
                    }
                    if (Constants.TransportMode.AIR.equals(kd.getTransMode()) || Constants.TransportMode.LAND.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getEta(), kd.getAta(), "k3", "k341", "k342"));
                    } else if (Constants.TransportMode.SEA.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getEta(), kd.getAta(), "k3", "k343", "k344"));
                    }
                    list.add(this.setValue(kd.getId(), user, now, kd.getDofrb(), kd.getDonbt(), "k4", "k44"));
                    if (Constants.TransportMode.FCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoubol(), kd.getAtd(), "k5", "k541"));
                    } else if (Constants.TransportMode.LCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoubol(), kd.getAtd(), "k5", "k542"));
                    }
                    list.add(this.setValue(kd.getId(), user, now, kd.getDoubold(), kd.getEtd(), "k6", "k64"));
//                    list.add(this.setValue(kd.getId(), user, now, kd.getDocds(), kd.getEtd(), "k7", "k74"));
                    list.add(this.setValue(kd.getId(), user, now, kd.getDocds(), kd.getAtd(), "k7", "k74"));
                    break;
                case "s10":
                    kd.setIdDate(kd.getAta());
                    if (Constants.TransportMode.AIR.equals(kd.getTransMode()) || Constants.TransportMode.LAND.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getEta(), kd.getAta(), "k3", "k351", "k352"));
                    } else if (Constants.TransportMode.SEA.equals(kd.getTransMode())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getEta(), kd.getAta(), "k3", "k353", "k354"));
                    }
                    list.add(this.setValue(kd.getId(), user, now, kd.getDofrb(), kd.getDonbt(), "k4", "k45"));
                    if (Constants.TransportMode.FCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoubol(), kd.getAtd(), "k5", "k551"));
                    } else if (Constants.TransportMode.LCL.equals(kd.getTransType())) {
                        list.add(this.setValue(kd.getId(), user, now, kd.getDoubol(), kd.getAtd(), "k5", "k552"));
                    }
                    list.add(this.setValue(kd.getId(), user, now, kd.getDoubold(), kd.getEtd(), "k6", "k65"));
                    list.add(this.setValue(kd.getId(), user, now, kd.getDocds(), kd.getEtd(), "k7", "k75"));
                    break;
                default:
                    break;
            }
            for (Iterator<TbKpiResult> it = list.iterator(); it.hasNext(); ) {
                TbKpiResult kr = (TbKpiResult) it.next();
                if (null == kr) {
                    it.remove();
                }
            }
            if (Utils.isNotEmpty(list)) {
                this.tbKpiResultDao.saveAll(list);
            }
            this.tbKpiDataDao.save(kd);
        }
        return null;
    }

    /**
     * <p>Description: 设置KPI结果</p>
     *
     * @param id   KPI数据ID
     * @param user 用户
     * @param now  日期
     * @param b    日期B
     * @param a    日期A
     * @param type KPI类型
     * @param code KPI计算参数
     * @return KPI结果
     */
    private TbKpiResult setValue(Long id, LoginUser user, Date now, Date b, Date a, String type, String... code) {
        TbKpiResult kpi = this.tbKpiResultDao.findByKpiIdAndType(id, type);
        if (null != b && null != a) {
            if (null == kpi) {
                kpi = new TbKpiResult();
                kpi.setKpiId(id);
                kpi.init(user, now);
                kpi.setType(type);
            } else {
                kpi.update(user, now);
            }
            kpi.setB(b);
            kpi.setA(a);
            int days = DateUtils.getDutyDays(kpi.getB(), kpi.getA());
            kpi.setDays(days);
            if (2 == code.length) {
                Integer min = Utils.parseInt(DictUtils.getValue(Constants.DictType.KPI_SETTINGS, code[0]));
                Integer max = Utils.parseInt(DictUtils.getValue(Constants.DictType.KPI_SETTINGS, code[1]));
                kpi.setResult(Utils.ok((min <= days && max >= days)));
            } else {
                Integer max = Utils.parseInt(DictUtils.getValue(Constants.DictType.KPI_SETTINGS, code[0]));
                kpi.setResult(Utils.ok((0 <= max - days)));
            }
            return kpi;
        } else if (null != kpi) {
            this.tbKpiResultDao.delete(kpi);
        }
        return null;
    }

}
