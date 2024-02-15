package net.pitan76.mcpitanlibchecker;

public class Messages {
    public static String HEADER = " is required to run the following mods.";
    public static String FOOTER1 = "Click the Download button to download MCPitanLib and other required files or";
    public static String FOOTER2 = "Open browser to download MCPitanLib and other required files.";

    public static String DOWNLOAD_BTN = "Download";
    public static String CLOSE_BTN = "Close";

    public static String DO_DOWNLOAD = "Do you want to download MCPitanLib and other required files? (Y/N): ";

    public static String DOWNLOADED_MSG = "MCPitanLib has been downloaded to the mods folder. Please restart Minecraft.";

    public static void init() {
        String lang = System.getProperty("user.language");
        switch (lang) {
            case "ja":
                japanese();
                break;
            case "ko":
                korean();
                break;
            case "zh":
                chinese();
                break;
            case "ru":
                russian();
                break;
            default:
                english();
                break;
        }
    }

    public static void english() {
        HEADER = " is required to run the following mods.";
        FOOTER1 = "Click the Download button to download MCPitanLib and other required files or";
        FOOTER2 = "Open browser to download MCPitanLib and other required files.";

        DOWNLOAD_BTN = "Download";
        CLOSE_BTN = "Close";

        DO_DOWNLOAD = "Do you want to download MCPitanLib and other required files? (Y/N): ";

        DOWNLOADED_MSG = "MCPitanLib has been downloaded to the mods folder. Please restart Minecraft.";
    }

    public static void japanese() {
        HEADER = " は以下のModを動かすために必要となります。";
        FOOTER1 = "ダウンロードボタンをクリックしてMCPitanLibと他の必要なファイルをダウンロードするか、";
        FOOTER2 = "ブラウザを開いてMCPitanLibとその他必要なファイルをダウンロードしてください。";

        DOWNLOAD_BTN = "ダウンロード";
        CLOSE_BTN = "閉じる";

        DO_DOWNLOAD = "MCPitanLibとその他必要なファイルをダウンロードしますか？ (Y/N): ";

        DOWNLOADED_MSG = "MCPitanLibがmodsフォルダにダウンロードされました。Minecraftを再起動してください。";
    }

    public static void korean() {
        HEADER = "은(는) 다음 모드를 실행하기 위해 필요합니다.";
        FOOTER1 = "다운로드 버튼을 클릭하여 MCPitanLib 및 기타 필요한 파일을 다운로드하거나";
        FOOTER2 = "브라우저를 열어 MCPitanLib 및 기타 필요한 파일을 다운로드하십시오.";

        DOWNLOAD_BTN = "다운로드";
        CLOSE_BTN = "닫기";

        DO_DOWNLOAD = "MCPitanLib 및 기타 필요한 파일을 다운로드 하시겠습니까? (Y/N): ";

        DOWNLOADED_MSG = "MCPitanLib가 mods 폴더에 다운로드되었습니다. Minecraft를 다시 시작하십시오.";
    }

    public static void chinese() {
        HEADER = "需要以下Mod才能运行。";
        FOOTER1 = "单击下载按钮下载MCPitanLib和其他必需文件，或";
        FOOTER2 = "打开浏览器下载MCPitanLib和其他必需文件。";

        DOWNLOAD_BTN = "下载";
        CLOSE_BTN = "关闭";

        DO_DOWNLOAD = "是否要下载MCPitanLib和其他必需文件？ (Y/N): ";

        DOWNLOADED_MSG = "MCPitanLib已下载到mods文件夹。请重新启动Minecraft。";
    }

    public static void russian() {
        HEADER = "необходим для запуска следующих модов.";
        FOOTER1 = "Нажмите кнопку Загрузить, чтобы загрузить MCPitanLib и другие необходимые файлы или";
        FOOTER2 = "Откройте браузер, чтобы загрузить MCPitanLib и другие необходимые файлы.";

        DOWNLOAD_BTN = "Скачать";
        CLOSE_BTN = "Закрыть";

        DO_DOWNLOAD = "Вы хотите загрузить MCPitanLib и другие необходимые файлы? (Y/N): ";

        DOWNLOADED_MSG = "MCPitanLib был загружен в папку mods. Пожалуйста, перезапустите Minecraft.";
    }
}
