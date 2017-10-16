package com.oxsoft.feh

import java.util.*

class Fields {
    companion object {
        val STAGE_1_1 = parse("RRRRRR RRRRRR NRRNNN NNNNNN NNNNRN NNFRFN RRRRRR RRRRRR ")
        val STAGE_1_2 = parse("NFNRNF FNNNNN NNNRNN NNNRNN NNRRNN NNRNNN NNNNNN NRRNNN ")
        val STAGE_1_3 = parse("NNNNNN FNNNNN NNNNNN NNNNFN NNNNNN RRNNRR NNNNNR NNNNRR ")
        val STAGE_1_4 = parse("NNFFNN NNNNNN NRRNRR NNNNRN NNRNNN NRRNRR NNNNNN FFNRNN ")
        val STAGE_1_5 = parse("FNNRNN NNNNNN NNRRRN NRNNRN NNNNRN NRRNNN NNNNNN FNRFNF ")
        val STAGE_2_1 = parse("RNNFNR RRNNRR NRRNNN NNRRRN NFNRNN NNRRNN NNNNNN FNRNFN ")
        val STAGE_2_2 = parse("NNNNNN NWNNWN NWWWWN NNNNNN WWNNWW FNNNNF NNNNNN FNNNNF ")
        val STAGE_2_3 = parse("FFFFFF RNNNRF NNFNNN NRFFRN NFFNNN NNRFNN FNNNNF RFNNFR ")
        val STAGE_2_4 = parse("RRRRRR NNNNNN RRRRNR NNNNNN RNRNRR RNNNNN RRNRRR NNNNNN ")
        val STAGE_2_5 = parse("WWWWWW NNNNNN NNNNNN WNNNNW NNNNNN WNNNNW NNNNNN WNNNNW ")
        val STAGE_3_1 = parse("NNNRRR NNNNRR NNNNNR RNNNNF NNNFNN RRRNNN NNNNNF FNNNNN ")
        val STAGE_3_2 = parse("NNRRRR NNNNNN NNRRNN NRRRNN NRRRRN NNNRNN NRNNNN NRRRNN ")
        val STAGE_3_3 = parse("NNNWNN NNNWNW NRNNNN NNRNNN NNNRNN NRNNNR NNNNNN NNNNNN ")
        val STAGE_3_4 = parse("RRRRNN RRRRNN NNRRNN NNRRNN NNNRNN NRNRNN FRNNNN FRRRRR ")
        val STAGE_3_5 = parse("NNRNNN NNRNNN NRRNNN NNNNNN NNNNRN RRRRNN NNNNNN NNNNNN ")
        val STAGE_4_1 = parse("")
        val STAGE_4_2 = parse("")
        val STAGE_4_3 = parse("")
        val STAGE_4_4 = parse("")
        val STAGE_4_5 = parse("")
        val STAGE_5_1 = parse("")
        val STAGE_5_2 = parse("")
        val STAGE_5_3 = parse("")
        val STAGE_5_4 = parse("")
        val STAGE_5_5 = parse("")
        val STAGE_6_1 = parse("")
        val STAGE_6_2 = parse("")
        val STAGE_6_3 = parse("")
        val STAGE_6_4 = parse("")
        val STAGE_6_5 = parse("")
        val STAGE_7_1 = parse("")
        val STAGE_7_2 = parse("")
        val STAGE_7_3 = parse("")
        val STAGE_7_4 = parse("")
        val STAGE_7_5 = parse("")
        val STAGE_8_1 = parse("")
        val STAGE_8_2 = parse("")
        val STAGE_8_3 = parse("")
        val STAGE_8_4 = parse("")
        val STAGE_8_5 = parse("")
        val STAGE_9_1 = parse("")
        val STAGE_9_2 = parse("")
        val STAGE_9_3 = parse("")
        val STAGE_9_4 = parse("")
        val STAGE_9_5 = parse("")

        private fun parse(src: String): Field {
            val rows = ArrayList<Array<CellType>>()
            val row = ArrayList<CellType>()
            src.forEach {
                when (it) {
                    'N' -> row.add(CellType.NORMAL)
                    'F' -> row.add(CellType.FOREST)
                    'R' -> row.add(CellType.RIVER)
                    'W' -> row.add(CellType.WALL)
                    ' ' -> {
                        rows.add(row.toTypedArray())
                        row.clear()
                    }
                }
            }
            return Field(rows.toTypedArray())
        }
    }
}
