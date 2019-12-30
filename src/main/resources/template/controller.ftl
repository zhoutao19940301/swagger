package ${package};

import com.github.pagehelper.PageHelper;
import com.talkweb.edu.common.exception.ParamException;
import com.talkweb.edu.common.response.Response;
import com.talkweb.edu.common.response.constants.TkResponseCode;
import ${package?substring(0,package?last_index_of('.'))}.service.impl.${tableClass.shortClassName}Service;
import ${tableClass.fullClassName};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

<#assign dateTime = .now>
/**
* @Descriprion ${tableClass.shortClassName}控制层
* @Author  bigdata
* @Date  ${dateTime?string["yyyy-MM-dd HH:mm:ss"]}
*/
@Api(tags = "${tableClass.shortClassName}管理")
@RestController
@RequestMapping("/${tableClass.variableName}")
@Slf4j
@Validated
public class ${tableClass.shortClassName}Controller {

    @Resource
    private ${tableClass.shortClassName}Service ${tableClass.variableName}Service;

    @PostMapping("/insert")
    @ApiOperation(value = "新增", notes = "新增")
    public Response insert${tableClass.shortClassName}(@Valid @RequestBody ${tableClass.shortClassName} req){
        Response response = null;
        try {
            ${tableClass.variableName}Service.insertSelective(req);
            response = new Response();
        } catch (ParamException e) {
            log.error(e.getMessage(),e);
            response = new Response(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            response = new Response(TkResponseCode.ERROR_NORMAL);
        }
        return response;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "根据id删除", notes = "根据id删除")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int", paramType = "query")
    public Response delete${tableClass.shortClassName}(@NotNull(message = "id不能为空") @RequestParam Object id){
        Response response = null;
        try {
            ${tableClass.variableName}Service.deleteById(id);
            response = new Response();
        } catch (ParamException e) {
            log.error(e.getMessage(),e);
            response = new Response(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            response = new Response(TkResponseCode.ERROR_NORMAL);
        }
        return response;
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新", notes = "更新")
    public Response update${tableClass.shortClassName}(@Valid @RequestBody ${tableClass.shortClassName} req){
        Response response = null;
        try {
            ${tableClass.variableName}Service.updateById(req);
            response = new Response();
        } catch (ParamException e) {
            log.error(e.getMessage(),e);
            response = new Response(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            response = new Response(TkResponseCode.ERROR_NORMAL);
        }
        return response;
    }

    @GetMapping("/selectById")
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int", paramType = "query")
    public Response select${tableClass.shortClassName}(@NotNull(message = "id不能为空") @RequestParam Object id){
        Response response = null;
        try {
            ${tableClass.variableName}Service.selectById(id);
            response = new Response();
        } catch (ParamException e) {
            log.error(e.getMessage(),e);
            response = new Response(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            response = new Response(TkResponseCode.ERROR_NORMAL);
        }
        return response;
    }

    @PostMapping("/selectPage")
    @ApiOperation(value = "分页查询列表", notes = "分页查询列表")
    public Response<${tableClass.shortClassName}> selectPage(@Valid @RequestBody ${tableClass.shortClassName} req){
        Response response = null;
        try {
            //开始分页查询
            //PageHelper.startPage(req.getPageNum(),req.getPageSize());
            List<${tableClass.shortClassName}> list = ${tableClass.variableName}Service.selectByEntity(req);
            response = new Response(list);
        } catch (ParamException e) {
            log.error(e.getMessage(),e);
            response = new Response(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            response = new Response(TkResponseCode.ERROR_NORMAL);
        }
        return response;
    }

}