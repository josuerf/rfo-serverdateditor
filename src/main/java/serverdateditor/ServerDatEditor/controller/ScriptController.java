package serverdateditor.ServerDatEditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serverdateditor.ServerDatEditor.ScriptData;
import serverdateditor.ServerDatEditor.model.ApiResponse;
import serverdateditor.ServerDatEditor.service.ScriptLoaderService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("script")
public class ScriptController {

    @Autowired
    private ScriptLoaderService loaderService;

    @GetMapping("/{name}")
    public ResponseEntity<List<ScriptData>> getScriptFile(@PathVariable String name) {
        return ResponseEntity.ok(loaderService.loadDataList(name));
    }

    @PostMapping("/{scriptName}")
    public ResponseEntity<ApiResponse> salvarDados(@RequestBody List<Object> data, @PathVariable String scriptName) {
        try {
            loaderService.salvarDados(data, scriptName);
            return ResponseEntity.ok(new ApiResponse(true, "TUDO CERTO"));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse(false, "Tudo Errado"));
        }
    }
}
