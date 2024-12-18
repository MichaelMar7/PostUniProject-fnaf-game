package entity.animatronic;

public class CamNode {

    private String cam;
    CamNode[] nextCam = new CamNode[2];

    public CamNode(String cam) {
        this.cam = cam;
    }

    public void setNextCam(CamNode cam1, CamNode cam2) {
        nextCam[0] = cam1;
        nextCam[1] = cam2;
    }

    public String getName() { return cam; }

}
