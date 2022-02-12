public class Path implements BoardPiece{

    @Override
    public void tick() {
        // Do nothing
    }

    @Override
    public char asChar() {
        return '=';
    }

    @Override
    public String getImageDir() {
        return "Path.png";
    }
}
