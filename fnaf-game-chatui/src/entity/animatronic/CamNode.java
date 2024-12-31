package entity.animatronic;

import java.util.HashMap;
import java.util.Map;

public class CamNode {

    private CamNum cam;
    CamNode[] nextCam = new CamNode[2];

    private final static Map<CamNum, String> camNames = new HashMap<>() {{
        put(CamNum.CAM1A, "Show Stage");
        put(CamNum.CAM1B, "Dining Area");
        put(CamNum.CAM1C, "Pirate Cove");
        put(CamNum.CAM2A, "West Hall");
        put(CamNum.CAM2B, "West Hall Corner");
        put(CamNum.CAM3, "Supply Closest");
        put(CamNum.CAM4A, "East Hallway");
        put(CamNum.CAM4B, "East Hall Corner");
        put(CamNum.CAM5, "Backstage");
        put(CamNum.CAM6, "Kitchen");
        put(CamNum.CAM7, "Restrooms");
        put(CamNum.OFFICE, "Office");
    }};


    public CamNode(String cam) {
        this.cam = CamNum.valueOf(cam.toUpperCase());
    }

    public void setNextCam(CamNode cam1, CamNode cam2) {
        nextCam[0] = cam1;
        nextCam[1] = cam2;
    }

    public CamNum getCamNum() { return cam; }

    public String getCamName() {
        return camNames.get(cam);
    }

}
