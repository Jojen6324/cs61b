import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public Rasterer() {
        // YOUR CODE HERE

    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        System.out.println(params);
        Map<String, Object> results = new HashMap<>();

        double lrlon = params.get("lrlon");
        double ullon = params.get("ullon");
        double lrlat = params.get("lrlat");
        double ullat = params.get("ullat");

        /* if (lrlat > MapServer.ROOT_LRLAT || lrlon > MapServer.ROOT_LRLON) {
            results.put("query_success", false);
            return results;
        } */

        double w = params.get("w");
        String[][] grid;
        int depth = 0;

        double lonDPP = (MapServer.ROOT_LRLON - MapServer.ROOT_ULLON) / MapServer.TILE_SIZE;
        /* Calculate depth */
        while (lonDPP > (lrlon - ullon) / w) {
            depth += 1;
            lonDPP = (MapServer.ROOT_LRLON - MapServer.ROOT_ULLON) / (256 * Math.pow(2, depth));
            if (depth == 7) {
                break;
            }
        }

        /* Calculate longitude */
        double ul_lon = MapServer.ROOT_ULLON;
        int x = (int) (Math.floor(((ullon - ul_lon) / lonDPP) / 256));
        int x1 = (int) (Math.floor(((lrlon - ul_lon) / lonDPP) / 256));
        double lr_lon = (x1 + 1)  * lonDPP * 256 + ul_lon;
        ul_lon = ul_lon + x * lonDPP * 256;

        /* Calculate latitude */
        double ul_lat = MapServer.ROOT_ULLAT;
        double latDPP = (ul_lat - MapServer.ROOT_LRLAT) / (256 * Math.pow(2, depth));
        int y = (int) (Math.floor(((ul_lat - ullat) / latDPP) / 256));
        int y1 = (int) (Math.floor(((ul_lat - lrlat) / latDPP) / 256));
        double lr_lat = ul_lat - (y1 + 1) * latDPP * 256;
        ul_lat = ul_lat - y * latDPP * 256;

        /* Generate grid including img name */
        grid = new String[y1 - y + 1][x1 - x + 1];
        for (int i = 0; i < y1 - y + 1; i += 1) {
            for (int j = 0; j < x1 - x + 1; j += 1) {
                grid[i][j] = "d" + depth + "_x" + (j + x) + "_y" + (y + i) + ".png";
            }
        }

        results.put("render_grid", grid);
        results.put("raster_lr_lon", lr_lon);
        results.put("raster_ul_lon", ul_lon);
        results.put("raster_lr_lat", lr_lat);
        results.put("raster_ul_lat", ul_lat);
        results.put("depth", depth);
        results.put("query_success", true);


        return results;
    }

}
