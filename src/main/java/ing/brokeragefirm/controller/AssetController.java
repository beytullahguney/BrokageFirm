package ing.brokeragefirm.controller;

import ing.brokeragefirm.model.Asset;
import ing.brokeragefirm.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("/deposit")
    public ResponseEntity<Asset> deposit(@RequestParam Long customerId, @RequestParam Integer amount) {
        Asset asset = assetService.depositMoney(customerId, amount);
        return ResponseEntity.ok(asset);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Asset> withdraw(@RequestParam Long customerId, @RequestParam Integer amount) {
        Asset asset = assetService.withdrawMoney(customerId, amount);
        return ResponseEntity.ok(asset);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Asset>> listAssets(@RequestParam Long customerId) {
        List<Asset> assets = assetService.listAssets(customerId);
        return ResponseEntity.ok(assets);
    }
}
