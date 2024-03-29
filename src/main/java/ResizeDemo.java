

public class ResizeDemo {
    public static void main(String[] args) {
        Picture inputImg;
        int removeColumns;
        int removeRows;

        if (args.length != 3) {
            System.out.println("Warning: Illegal Argument!");
            System.out.println("Usage: java ResizeDemo [image filename] [num cols to remove] [num rows to remove]");

            inputImg = new Picture("D:/3.png");
            removeColumns = inputImg.width() / 4;
            removeRows = inputImg.height() / 4;
        } else {
            inputImg = new Picture(args[0]);
            removeColumns = Integer.parseInt(args[1]);
            removeRows = Integer.parseInt(args[2]);
        }

        System.out.printf("image is %d columns by %d rows\n", inputImg.width(), inputImg.height());
        SeamCarver sc = new SeamCarver(inputImg);

        long start = System.currentTimeMillis();

        for (int i = 0; i < removeRows; i++) {
            int[] horizontalSeam = sc.findHorizontalSeam();
            sc.removeHorizontalSeam(horizontalSeam);
        }

        for (int i = 0; i < removeColumns; i++) {
            int[] verticalSeam = sc.findVerticalSeam();
            sc.removeVerticalSeam(verticalSeam);
        }
        Picture outputImg = sc.picture();

        System.out.printf("new image size is %d columns by %d rows\n", sc.width(), sc.height());

        long end = System.currentTimeMillis();
        System.out.println("Resizing time: " + (end-start)/1E3 + " seconds.");
        inputImg.show();
        outputImg.show();
    }
}
