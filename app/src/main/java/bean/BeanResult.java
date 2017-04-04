package bean;

import java.util.List;

/**
 * 1.类的用途
 * 2.@author:zhanghaisheng
 * 3.@2017/3/19
 */


public class BeanResult {


    /**
     * reason : success
     * result : [{"day":"10/10","date":"前638年10月10日","title":"泓水之战，楚国击败宋国，成为霸主","e_id":"11514"},{"day":"10/10","date":"648年10月10日","title":"唐朝经学家孔颖达去世","e_id":"11515"},{"day":"10/10","date":"732年10月10日","title":"图尔战役（普瓦蒂埃战役）爆发","e_id":"11516"},{"day":"10/10","date":"1492年10月10日","title":"烟草渊源说","e_id":"11517"},{"day":"10/10","date":"1813年10月10日","title":"意大利作曲家威尔第诞辰","e_id":"11518"},{"day":"10/10","date":"1868年10月10日","title":"古巴爆发第一次独立战争","e_id":"11519"},{"day":"10/10","date":"1881年10月10日","title":"中国近代第一张大学文凭获得者王宠惠出生","e_id":"11520"},{"day":"10/10","date":"1889年10月10日","title":"德国钢琴家阿道夫·冯·亨泽尔特逝世","e_id":"11521"},{"day":"10/10","date":"1895年10月10日","title":"林语堂诞辰","e_id":"11522"},{"day":"10/10","date":"1896年10月10日","title":"萧三诞辰","e_id":"11523"},{"day":"10/10","date":"1911年10月10日","title":"辛亥革命爆发","e_id":"11524"},{"day":"10/10","date":"1913年10月10日","title":"袁世凯就任正式大总统","e_id":"11525"},{"day":"10/10","date":"1913年10月10日","title":"巴拿马运河开通","e_id":"11526"},{"day":"10/10","date":"1919年10月10日","title":"孙中山改组中华革命党","e_id":"11527"},{"day":"10/10","date":"1919年10月10日","title":"福建省厦门双十中学创办","e_id":"11528"},{"day":"10/10","date":"1923年10月10日","title":"曹锟就任巨额买来的大总统","e_id":"11529"},{"day":"10/10","date":"1925年10月10日","title":"故宫首次对外开放","e_id":"11530"},{"day":"10/10","date":"1926年10月10日","title":"中国历史博物馆开馆","e_id":"11531"},{"day":"10/10","date":"1926年10月10日","title":"北伐军攻克武昌","e_id":"11532"},{"day":"10/10","date":"1934年10月10日","title":"中央红军开始长征","e_id":"11533"},{"day":"10/10","date":"1938年10月10日","title":"八女投江","e_id":"11534"},{"day":"10/10","date":"1945年10月10日","title":"朝鲜共产党成立","e_id":"11535"},{"day":"10/10","date":"1945年10月10日","title":"国共签署《双十协定》","e_id":"11536"},{"day":"10/10","date":"1947年10月10日","title":"中共中央公布土地法大纲","e_id":"11537"},{"day":"10/10","date":"1948年10月10日","title":"辽沈战役之塔山阻击战","e_id":"11538"},{"day":"10/10","date":"1964年10月10日","title":"第18届奥运会在东京开幕","e_id":"11539"},{"day":"10/10","date":"1970年10月10日","title":"斐济群岛宣布独立","e_id":"11540"},{"day":"10/10","date":"1980年10月10日","title":"金日成提出建立高丽民主联邦共和国方案","e_id":"11541"},{"day":"10/10","date":"1980年10月10日","title":"保证民用航空安全的《海牙公约》生效","e_id":"11542"},{"day":"10/10","date":"1980年10月10日","title":"人民艺术家赵丹逝世","e_id":"11543"},{"day":"10/10","date":"1984年10月10日","title":"中德联营的大众汽车公司成立","e_id":"11544"},{"day":"10/10","date":"1985年10月10日","title":"古代印第安晷遗迹被发现","e_id":"11545"},{"day":"10/10","date":"1990年10月10日","title":"版画家刘岘逝世","e_id":"11546"},{"day":"10/10","date":"1991年10月10日","title":"中国作家协会顾问陈学昭逝世","e_id":"11547"},{"day":"10/10","date":"1991年10月10日","title":"曼德拉住宅遭恐怖分子袭击","e_id":"11548"},{"day":"10/10","date":"1992年10月10日","title":"第一个\u201c世界精神卫生日\u201d","e_id":"11549"},{"day":"10/10","date":"1992年10月10日","title":"\u201c天书\u201d敦煌曲谱被破译","e_id":"11550"},{"day":"10/10","date":"1992年10月10日","title":"书法家沙孟海逝世","e_id":"11551"},{"day":"10/10","date":"1993年10月10日","title":"韩国一客轮海上遇难","e_id":"11552"},{"day":"10/10","date":"1994年10月10日","title":"世界最长的滑道在北京建成","e_id":"11553"},{"day":"10/10","date":"2000年10月10日","title":"美国总统克林顿签署对华永久正常贸易关系法案","e_id":"11554"},{"day":"10/10","date":"2000年10月10日","title":"河南南阳发现一批罕见的铭文青铜器","e_id":"11555"},{"day":"10/10","date":"2001年10月10日","title":"慕绥新马向东贪污受贿被严惩","e_id":"11556"},{"day":"10/10","date":"2005年10月10日","title":"吉列被宝洁公司收购","e_id":"11557"},{"day":"10/10","date":"2008年10月10日","title":"芬兰前总统阿赫蒂萨里获诺贝尔和平奖","e_id":"11558"},{"day":"10/10","date":"2015年10月10日","title":"土耳其遭恐袭95死","e_id":"11559"},{"day":"10/10","date":"2015年10月10日","title":"央行释放七万亿","e_id":"11560"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * day : 10/10
         * date : 前638年10月10日
         * title : 泓水之战，楚国击败宋国，成为霸主
         * e_id : 11514
         */

        private String day;
        private String date;
        private String title;
        private String e_id;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getE_id() {
            return e_id;
        }

        public void setE_id(String e_id) {
            this.e_id = e_id;
        }
    }
}
