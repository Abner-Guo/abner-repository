package pers.guo.repositorytemplate;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.wildfly.common.Assert;
import pers.guo.repositorytemplate.common.CoordinateUtil;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/6/2 08:36
 */
public class CoordinateUtilTest {

    @Test
    public void getLatLonMap() {

        String a="116.616634,40.272665|116.604626,40.269517|116.604256,40.267022|116.582632,40.262007|116.578637,40.268173|116.575027,40.260284|116.542714,40.249840|116.540721,40.256634|116.530844,40.256761|116.529081,40.242442|116.518795,40.238155|116.508835,40.250610|116.502856,40.235876|116.494887,40.245896|116.482931,40.241651|116.478228,40.238911|116.491567,40.236873|116.484921,40.232892|116.493219,40.229194|116.489303,40.220479|116.476949,40.220260|116.474955,40.212485|116.472963,40.218813|116.470493,40.210451|116.461000,40.220081|116.449029,40.208498|116.449031,40.223531|116.445337,40.208680|116.434692,40.208993|116.434572,40.223483|116.445940,40.228981|116.420624,40.228557|116.417148,40.247080|116.411083,40.252140|116.398038,40.251971|116.397089,40.255021|116.396555,40.251012|116.399081,40.248318|116.409655,40.249588|116.409755,40.240340|116.385070,40.244620|116.375054,40.228341|116.369045,40.231866|116.363033,40.222390|116.342860,40.220934|116.352739,40.218952|116.345081,40.208936|116.351004,40.195352|116.367613,40.202974|116.367403,40.194606|116.349284,40.192942|116.367036,40.191520|116.364301,40.186238|116.375250,40.182781|116.348998,40.178180|116.326446,40.184397|116.326949,40.192618|116.323442,40.184390|116.311506,40.182870|116.323412,40.181363|116.317176,40.176629|116.290573,40.178533|116.299724,40.182851|116.286875,40.188295|116.278584,40.184827|116.286874,40.181287|116.282869,40.176338|116.264858,40.176086|116.244121,40.172817|116.242360,40.170818|116.244949,40.170733|116.245121,40.172560|116.264858,40.171562|116.266859,40.174147|116.284871,40.173312|116.286874,40.176130|116.287456,40.170252|116.281876,40.168828|116.306904,40.167816|116.310911,40.160271|116.327394,40.167345|116.340220,40.148166|116.352828,40.165121|116.385173,40.159104|116.385056,40.138803|116.344984,40.138503|116.320930,40.148526|116.320930,40.146416|116.339168,40.136718|116.320544,40.138497|116.316921,40.145479|116.305051,40.138854|116.337197,40.130912|116.285742,40.122827|116.305269,40.123227|116.302890,40.112222|116.284512,40.112474|116.260759,40.140718|116.272626,40.110588|116.289289,40.102829|116.274851,40.088198|116.303423,40.079384|116.326932,40.056681|116.328710,40.056890|116.327174,40.057130|116.327078,40.059032|116.323880,40.061837|116.323385,40.065341|116.318237,40.070194|116.317619,40.074166|116.338833,40.068783|116.333954,40.062901|116.346978,40.055998|116.363284,40.055225|116.348982,40.054824|116.343099,40.046917|116.352989,40.041418|116.356998,40.046995|116.355455,40.036939|116.361815,40.036950|116.352987,40.026920|116.343159,40.034917|116.349302,40.022928|116.373668,40.021610|116.366494,40.016956|116.375026,40.009423|116.381853,40.013794|116.380956,40.000975|116.395953,40.000987|116.381818,39.994975|116.397597,39.993538|116.407049,39.980352|116.408511,39.993530|116.417048,39.992806|116.424689,40.007340|116.447562,40.007514|116.447007,39.996109|116.454321,40.004945|116.456383,40.013544|116.472687,40.014904|116.465568,40.016921|116.466949,40.028928|116.478918,40.026113|116.488824,40.032861|116.482906,40.039300|116.496921,40.036769|116.500841,40.057098|116.529320,40.038742|116.524747,40.028710|116.536443,40.038987|116.574614,40.033374|116.599304,40.034627|116.578060,40.036092|116.577048,40.040207|116.581131,40.040113|116.580815,40.044431|116.582654,40.044588|116.584604,40.047695|116.582567,40.046676|116.582409,40.044834|116.580451,40.044796|116.579594,40.041654|116.575690,40.040644|116.572617,40.035463|116.543427,40.042705|116.544324,40.055064|116.565610,40.062660|116.556199,40.066678|116.561887,40.095427|116.568631,40.090357|116.574935,40.096335|116.574623,40.109041|116.584611,40.103016|116.586964,40.090636|116.578983,40.086643|116.588603,40.062747|116.593596,40.078632|116.583334,40.082639|116.597536,40.085697|116.594605,40.103453|116.606878,40.104905|116.608606,40.091323|116.610327,40.104914|116.615041,40.103063|116.618615,40.096789|116.614243,40.112635|116.620879,40.114899|116.627026,40.084642|116.626087,40.109182|116.628629,40.099757|116.635691,40.108653|116.630304,40.114976|116.640652,40.109256|116.642657,40.115341|116.661224,40.116694|116.643480,40.117486|116.640654,40.124435|116.630309,40.118324|116.638483,40.128658|116.628633,40.135386|116.624626,40.129956|116.613972,40.143993|116.554477,40.152497|116.568192,40.156664|116.554333,40.158689|116.562243,40.175087|116.587411,40.172644|116.586822,40.174848|116.578626,40.177665|116.562278,40.178300|116.563190,40.194677|116.556669,40.197323|116.556376,40.194690|116.561460,40.186679|116.558662,40.178163|116.548348,40.178364|116.544701,40.185943|116.532374,40.182745|116.531849,40.208750|116.552680,40.204841|116.558436,40.210688|116.544289,40.212303|116.547134,40.222714|116.532337,40.224345|116.532503,40.234996|116.554680,40.239426|116.556673,40.228613|116.558120,40.239244|116.570956,40.240360|116.558117,40.242140|116.560367,40.252992|116.592625,40.261461|116.604830,40.266448|116.605079,40.268201|116.612630,40.268144|116.616634,40.270424|116.616650,40.272642|116.618402,40.272659|116.616634,40.272665";

        Coordinate[] coordinateArray = CoordinateUtil.getCoordinateArray(a);

        System.out.println(coordinateArray);
    }


    @Test
    public void withinAndIntersects() {
        String a="116.56674900,39.98920100|116.281117,40.004536|116.300664,39.8567|116.555352,39.854484|116.56674900,39.98920100";
        String b="116.616634,40.272665|116.644733,40.280371|116.636181,40.264352|116.616634,40.272665";
        String c="116.616634,40.272665|116.644733,40.280371|116.636181,40.264352";
        String d="116.616634,40.272665|116.644733,40.280371";

        Coordinate[] latLonMap = CoordinateUtil.getCoordinateArray(a);
        //116.636037,40.270353
        Coordinate coordinate = new Coordinate(116.616634, 40.272665);
        Coordinate coordinate2 = new Coordinate(116.636037, 40.270353);
        Coordinate coordinate3 = new Coordinate(116.56674900, 39.98920100);
        Coordinate coordinate4 = new Coordinate(116.36024000, 39.93912300);

        System.out.println(CoordinateUtil.withinAndIntersects(latLonMap, coordinate));
        System.out.println(CoordinateUtil.withinAndIntersects(latLonMap, coordinate2));

        boolean within = CoordinateUtil.withinAndIntersects(latLonMap, coordinate3);
        System.out.println(within);
        System.out.println(CoordinateUtil.withinAndIntersects(latLonMap, coordinate4));
        Assert.assertTrue(within);
        Assert.assertTrue(CoordinateUtil.withinAndIntersects(latLonMap, coordinate4));
    }

}