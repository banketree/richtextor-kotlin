package com.mabeijianxi.jianxiexpression.core;

import com.mabeijianxi.jianxiexpression.R;

import java.util.HashMap;

/**
 * Created by jian on 2016/6/23.
 * mabeijianxi@gmail.com
 */
public class ExpressionCache {
    //    TODO 每页数据资源，可自己定义，自己更改
    public static final String[] page_1 = new String[]{
            "[可爱]", "[哈哈]", "[嘻嘻]",
            "[笑哭]", "[思考]", "[怒骂]",
            "[嘘]", "[鼓掌]", "[左哼哼]",
            "[右哼哼]", "[亲亲]", "[疑问]",
            "[拜拜]", "[呵呵]", "[阴险]",
            "[害羞]", "[挤眼]", "[打脸]",
            "[怒]", "[累]", ""
    };

    public static final String[] page_2 = new String[]{
            "[鄙视]", "[馋嘴]", "[吃惊]",
            "[打哈气]", "[悲伤]", "[闭嘴]",
            "[顶]", "[感冒]", "[汗]",
            "[爱你]", "[黑线]", "[哼]",
            "[花心]", "[可怜]", "[酷]",
            "[困]", "[懒得理你]", "[钱]",
            "[傻眼]", "[生病]", ""
    };
    public static final String[] page_3 = new String[]{
            "[失望]", "[睡觉]", "[抓狂]",
            "[太开心]", "[偷笑]", "[吐]",
            "[挖鼻屎]", "[委屈]", "[晕]",
            "[帅]", "[狗]", "[猫]",
            "[熊猫]", "[兔子]", "[猪头]",
            "[神兽]", "[男孩儿]", "[女孩儿]",
            "[肥皂]", "[奥特曼]", ""
    };
    public static final String[] page_4 = new String[]{
            "[给力]", "[囧]", "[萌]",
            "[神马]", "[v5]", "[双喜]",
            "[织]", "[不要]", "[棒]",
            "[六六]", "[来]", "[ok]",
            "[弱]", "[握手]", "[耶]",
            "[赞]", "[作揖]", "[伤心]",
            "[心]", "[蛋糕]", ""
    };
    public static final String[] page_5 = new String[]{
            "[飞机]", "[干杯]", "[话筒]",
            "[蜡烛]", "[礼物]", "[绿丝带]",
            "[围脖]", "[围观]", "[音乐]",
            "[照相机]", "[钟]", "[微风]",
            "[鲜花]", "[太阳]", "[月亮]",
            "[浮云]", "[下雨]", "[沙尘暴]",
            "", "", ""
    };

    /**
     * 所有的表情资源id缓存，增加效率
     */
    private static HashMap<String, Integer> allExpressionTable = new HashMap<String, Integer>();

    static {

        allExpressionTable.put(page_1[0], R.drawable.aa_keai
        );
        allExpressionTable.put(page_1[1], R.drawable.ab_haha
        );
        allExpressionTable.put(page_1[2], R.drawable.ac_xixi
        );
        allExpressionTable.put(page_1[3], R.drawable.ad_xiaoku
        );
        allExpressionTable.put(page_1[4], R.drawable.ae_sikao
        );
        allExpressionTable.put(page_1[5], R.drawable.af_numa
        );
        allExpressionTable.put(page_1[6], R.drawable.ag_xu
        );
        allExpressionTable.put(page_1[7], R.drawable.ah_guzhang
        );
        allExpressionTable.put(page_1[8], R.drawable.ai_zuohengheng
        );
        allExpressionTable.put(page_1[9], R.drawable.aj_youhengheng
        );
        allExpressionTable.put(page_1[10], R.drawable.ak_qinqin
        );
        allExpressionTable.put(page_1[11], R.drawable.al_yiwen
        );
        allExpressionTable.put(page_1[12], R.drawable.am_baibai
        );
        allExpressionTable.put(page_1[13], R.drawable.an_hehe
        );
        allExpressionTable.put(page_1[14], R.drawable.ao_yinxian
        );
        allExpressionTable.put(page_1[15], R.drawable.ap_haixiu
        );
        allExpressionTable.put(page_1[16], R.drawable.aq_jiyan
        );
        allExpressionTable.put(page_1[17], R.drawable.ar_dalian
        );
        allExpressionTable.put(page_1[18], R.drawable.as_nu
        );
        allExpressionTable.put(page_1[19], R.drawable.at_lei
        );


        allExpressionTable.put(page_2[0], R.drawable.ba_bishi
        );
        allExpressionTable.put(page_2[1], R.drawable.bb_chanzui
        );
        allExpressionTable.put(page_2[2], R.drawable.bc_chijing
        );
        allExpressionTable.put(page_2[3], R.drawable.bd_dahaqi
        );
        allExpressionTable.put(page_2[4], R.drawable.be_beishang
        );
        allExpressionTable.put(page_2[5], R.drawable.bf_bizui
        );
        allExpressionTable.put(page_2[6], R.drawable.bg_ding
        );
        allExpressionTable.put(page_2[7], R.drawable.bh_ganmao
        );
        allExpressionTable.put(page_2[8], R.drawable.bi_han
        );
        allExpressionTable.put(page_2[9], R.drawable.bj_aini
        );
        allExpressionTable.put(page_2[10], R.drawable.bk_heixian
        );
        allExpressionTable.put(page_2[11], R.drawable.bl_heng
        );
        allExpressionTable.put(page_2[12], R.drawable.bm_huaxin
        );
        allExpressionTable.put(page_2[13], R.drawable.bn_kelian
        );
        allExpressionTable.put(page_2[14], R.drawable.bo_ku
        );
        allExpressionTable.put(page_2[15], R.drawable.bp_kun
        );
        allExpressionTable.put(page_2[16], R.drawable.bq_landelini
        );
        allExpressionTable.put(page_2[17], R.drawable.br_qian
        );
        allExpressionTable.put(page_2[18], R.drawable.bs_shayan
        );
        allExpressionTable.put(page_2[19], R.drawable.bt_shengbing
        );


        allExpressionTable.put(page_3[0], R.drawable.ca_shiwang
        );
        allExpressionTable.put(page_3[1], R.drawable.cb_shuijiao
        );
        allExpressionTable.put(page_3[2], R.drawable.cc_zhuakuang
        );
        allExpressionTable.put(page_3[3], R.drawable.cd_taikaixin
        );
        allExpressionTable.put(page_3[4], R.drawable.ce_touxiao
        );
        allExpressionTable.put(page_3[5], R.drawable.cf_tu
        );
        allExpressionTable.put(page_3[6], R.drawable.cg_wabishi
        );
        allExpressionTable.put(page_3[7], R.drawable.ch_weiqu
        );
        allExpressionTable.put(page_3[8], R.drawable.ci_yun
        );
        allExpressionTable.put(page_3[9], R.drawable.cj_shuai
        );
        allExpressionTable.put(page_3[10], R.drawable.ck_doge
        );
        allExpressionTable.put(page_3[11], R.drawable.cl_miao
        );
        allExpressionTable.put(page_3[12], R.drawable.cm_xiongmao
        );
        allExpressionTable.put(page_3[13], R.drawable.cn_tuzi
        );
        allExpressionTable.put(page_3[14], R.drawable.co_zhutou
        );
        allExpressionTable.put(page_3[15], R.drawable.cp_shenshou
        );
        allExpressionTable.put(page_3[16], R.drawable.cq_nanhaier
        );
        allExpressionTable.put(page_3[17], R.drawable.cr_nvhaier
        );
        allExpressionTable.put(page_3[18], R.drawable.cs_feizao
        );
        allExpressionTable.put(page_3[19], R.drawable.ct_aoteman

        );

        allExpressionTable.put(page_4[0], R.drawable.da_geili
        );
        allExpressionTable.put(page_4[1], R.drawable.db_jiong
        );
        allExpressionTable.put(page_4[2], R.drawable.dc_meng
        );
        allExpressionTable.put(page_4[3], R.drawable.dd_shenma
        );
        allExpressionTable.put(page_4[4], R.drawable.de_v5
        );
        allExpressionTable.put(page_4[5], R.drawable.df_xi
        );
        allExpressionTable.put(page_4[6], R.drawable.dg_zhi
        );
        allExpressionTable.put(page_4[7], R.drawable.dh_buyao
        );
        allExpressionTable.put(page_4[8], R.drawable.di_good
        );
        allExpressionTable.put(page_4[9], R.drawable.dj_haha
        );
        allExpressionTable.put(page_4[10], R.drawable.dk_lai
        );
        allExpressionTable.put(page_4[11], R.drawable.dl_ok
        );
        allExpressionTable.put(page_4[12], R.drawable.dm_ruo
        );
        allExpressionTable.put(page_4[13], R.drawable.dn_woshou
        );
        allExpressionTable.put(page_4[14], R.drawable.do_ye
        );
        allExpressionTable.put(page_4[15], R.drawable.dp_zan
        );
        allExpressionTable.put(page_4[16], R.drawable.dq_zuoyi
        );
        allExpressionTable.put(page_4[17], R.drawable.dr_shangxin
        );
        allExpressionTable.put(page_4[18], R.drawable.ds_xin
        );
        allExpressionTable.put(page_4[19], R.drawable.dt_dangao
        );


        allExpressionTable.put(page_5[0], R.drawable.ea_feiji
        );
        allExpressionTable.put(page_5[1], R.drawable.eb_ganbei
        );
        allExpressionTable.put(page_5[2], R.drawable.ec_huatong
        );
        allExpressionTable.put(page_5[3], R.drawable.ed_lazhu
        );
        allExpressionTable.put(page_5[4], R.drawable.ee_liwu
        );
        allExpressionTable.put(page_5[5], R.drawable.ef_lvsidai
        );
        allExpressionTable.put(page_5[6], R.drawable.eg_weibo
        );
        allExpressionTable.put(page_5[7], R.drawable.eh_weiguan
        );
        allExpressionTable.put(page_5[8], R.drawable.ei_yinyue
        );
        allExpressionTable.put(page_5[9], R.drawable.ej_zhaoxiangji
        );
        allExpressionTable.put(page_5[10], R.drawable.ek_zhong
        );
        allExpressionTable.put(page_5[11], R.drawable.el_weifeng
        );
        allExpressionTable.put(page_5[12], R.drawable.em_xianhua
        );
        allExpressionTable.put(page_5[13], R.drawable.en_taiyang
        );
        allExpressionTable.put(page_5[14], R.drawable.eo_yueliang
        );
        allExpressionTable.put(page_5[15], R.drawable.ep_fuyun
        );
        allExpressionTable.put(page_5[16], R.drawable.eq_xiayu
        );
        allExpressionTable.put(page_5[17], R.drawable.er_shachenbao
        );
    }

    /**
     * 最近使用表情缓存
     */
    private static String[] recentExpression = new String[21];
    /**
     * tab
     */
    private static String[] pageTitle;

    /**
     * 得到每类表情的标签，可拓展
     *
     * @return
     */
    public static String[] getPageTitle() {
//        String category_1 = "最近";
        String category_2 = "表情";
//        String category_3 = "表二";
        if (pageTitle == null) {
            pageTitle = new String[]{category_2};//category_1,
        }
        return pageTitle;
    }

    public static String[] getRecentExpression() {
        return recentExpression;
    }

    public static HashMap<String, Integer> getAllExpressionTable() {
        return allExpressionTable;
    }
}