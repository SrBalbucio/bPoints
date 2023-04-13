package xyz.balbucio.bpoint.spigot.logs;

public enum LogType {

    SET{
        @Override
        public String get(){
            return "§7O§f %player% §7teve§f %amount% bPoints §7setado pelo§f %admin%§7!";
        }
    }, REMOVER{
        @Override
        public String get(){
            return "§7O§f %player% §7teve§f %amount% bPoints §7removidos pelo§f %admin%§7!";
        }
    }, ADD{
        @Override
        public String get(){
            return "§7O§f %player% §7teve§f %amount% bPoints §7adicionados pelo§f %admin%§7!";
        }
    }, CLEAR{
        @Override
        public String get(){
            return "§7O§f %player% §7teve seus bPoints retirados pelo§f %admin%§7!";
        }
    };

    public abstract String get();
}
