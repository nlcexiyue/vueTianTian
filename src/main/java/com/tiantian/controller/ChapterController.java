package com.tiantian.controller;


import com.github.pagehelper.PageInfo;
import com.tiantian.dto.ResultData;
import com.tiantian.entity.Chapter;
import com.tiantian.service.ChapterService;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ChapterController {
    @Autowired
    private ChapterService chapterService;


    //列表
    @RequestMapping("/chapter/list")
    @ResponseBody
    public PageInfo<Chapter> list(
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer pageSize){
        PageInfo<Chapter> pageInfo=chapterService.list(page,pageSize);
//        model.addAttribute("list",pageInfo.getList());
//        model.addAttribute("pageInfo",pageInfo);
        return pageInfo;
    }

//添加
    @RequestMapping("/chapter/addPage")
    public String addPage(){
        return "chapter/chapter-add";
    }

    //    添加
    @RequestMapping("/chapter/add")
    @ResponseBody
    public ResultData add(Chapter chapter){
        return chapterService.add(chapter);
    }


//    编辑
    @RequestMapping("/chapter/editPage")
    public String editPage(Model model, Integer chapterId){
        Chapter dbChapter = chapterService.findById(chapterId);
        model.addAttribute("chapter",dbChapter);

        return "chapter/chapter-edit";
    }

    //更新基本信息
    @RequestMapping("/chapter/edit")
    @ResponseBody
    public ResultData edit(Chapter chapter){
        System.out.println("qqqqqqqqqqqq"+chapter.toString());
        return  chapterService.edit(chapter);
    }

    //    search
    @RequestMapping("/chapter/search")
    public String searchList(Model model,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "3") Integer pageSize,
                             String keyword) {
        PageInfo<Chapter> pageInfo=chapterService.searchList(page,pageSize,keyword);

        model.addAttribute("list",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        return  "chapter/chapter-list";
    }
    //      批量删除
    @RequestMapping("/chapter/deleteAll")
    @ResponseBody
    public ResultData deleteAll(@RequestParam(name="ids") String ids){
        //将ids转为数组
        String[] idArr = ids.split(",");
        //将字符串数组转为int数组
        return chapterService.batchDelete(idArr);

    }

    @RequestMapping("/chapter/findByBookId")
    @ResponseBody
    public List<Chapter> findByBookId(Integer bookId){
        List<Chapter> chapterList = chapterService.findByBookId(bookId);
        int a = 0;
        return chapterList;
    }

    @RequestMapping("/chapter/upload")
    @ResponseBody
    public ResultData upload(MultipartFile file){
        int a=10;
        return chapterService.upload(file);
    }

    @RequestMapping("/chapter/readDoc")
    @ResponseBody
    public List<String> readDoc(){
        String content = readWord("C:\\Users\\cq\\Desktop\\新建 Microsoft Word 文档.docx");
        String[] split = content.split("\n");
        List<String> list = Arrays.asList(split);
        return list;


    }

    public static String readWord(String path) {
        String buffer = "";
        try {
            if (path.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
                is.close();
            } else if (path.endsWith("docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();
                opcPackage.close();
            } else {
                System.out.println("此文件不是word文件！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer;
    }
}
