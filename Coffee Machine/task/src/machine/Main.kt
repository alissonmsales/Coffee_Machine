package machine

enum class MachineStates(val state: String) {
    BUY("buy"),
    BUY_ITEM("buy_item"),
    FILL("fill"),
    FILL_WATER("fill_water"),
    FILL_MILK("fill_milk"),
    FILL_BEANS("fill_beans"),
    FILL_DISPOSABLE_CUPS("fill_disposable_cups"),
    TAKE("take"),
    REMAINING("remaining"),
    EXIT("exit"),
    INIT("init");

    companion object {
        fun findByState(state: String): MachineStates {
            for (enum in MachineStates.values()) {
                if (state == enum.state) return enum
            }
            return INIT
        }
    }
}

class CoffeeMachine() {

    // initialize stock
    var totalWater = 400
    var totalMilk = 540
    var totalBeans = 120
    var totalDisposableCups = 9
    var totalMoney = 550
    var currentState = MachineStates.INIT

    init {
        printAction()
    }

    fun action(content: String): Boolean {
        if (currentState == MachineStates.INIT) {
            currentState = MachineStates.findByState(content)
        }

        when(currentState) {
            MachineStates.REMAINING -> {
                printStock(totalWater, totalMilk, totalBeans, totalDisposableCups, totalMoney)

                printAction()
                currentState = MachineStates.INIT
                return true
            }
            MachineStates.BUY -> {
                print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                currentState = MachineStates.BUY_ITEM
                return true
            }
            MachineStates.BUY_ITEM -> {
                if (content == "back") {
                    currentState = MachineStates.INIT
                    printAction()
                    return true
                } else {
                    buyItem(content.toInt())
                }

                currentState = MachineStates.INIT
                printAction()
                return true
            }
            MachineStates.FILL -> {
                print("Write how many ml of water do you want to add: ")
                currentState = MachineStates.FILL_WATER
                return true;
            }
            MachineStates.FILL_WATER -> {
                totalWater += content.toInt()
                print("Write how many ml of milk do you want to add: ")
                currentState = MachineStates.FILL_MILK
                return true
            }
            MachineStates.FILL_MILK -> {
                totalMilk += content.toInt()
                print("Write how many grams of coffee beans do you want to add: ")
                currentState = MachineStates.FILL_BEANS
                return true
            }
            MachineStates.FILL_BEANS -> {
                totalBeans += content.toInt()
                print("Write how many disposable cups of coffee do you want to add: ")
                currentState = MachineStates.FILL_DISPOSABLE_CUPS
                return true
            }
            MachineStates.FILL_DISPOSABLE_CUPS -> {
                totalDisposableCups += content.toInt()
                printAction()
                currentState = MachineStates.INIT
                return true
            }
            MachineStates.TAKE -> {
                println("I gave you $$totalMoney")
                totalMoney = 0

                currentState = MachineStates.INIT
                printAction()
                return true
            }
            MachineStates.EXIT -> {
                return false
            }
        }

        return true
    }

    private fun buyItem(i: Int) {
        if (checkStock(totalWater, totalMilk, totalBeans, totalDisposableCups, i)) {
            println("I have enough resources, making you a coffee!")
            totalDisposableCups--
            when (i) {
                1 -> {
                    totalWater -= 250
                    totalBeans -= 16
                    totalMoney += 4
                }
                2 -> {
                    totalWater -= 350
                    totalMilk -= 75
                    totalBeans -= 20
                    totalMoney += 7
                }
                3 -> {
                    totalWater -= 200
                    totalMilk -= 100
                    totalBeans -= 12
                    totalMoney += 6
                }
            }
        }
    }

    private fun printAction() {
        print("Write action (buy, fill, take, remaining, exit): ")
    }

    private fun printStock(wa: Int, mi: Int, be: Int, dc: Int, mo: Int) {
        println("The coffee machine has:")
        println("$wa of water")
        println("$mi of milk")
        println("$be of coffee beans")
        println("$dc of disposable cups")
        println("$mo of money")
    }

    private fun checkStock(totalWater:Int, totalMilk:Int, totalBeans:Int, totalDisposableCups:Int, item:Int): Boolean {
        if (totalDisposableCups == 0) return false

        when (item) {
            1 -> return totalWater >= 250 && totalBeans >= 16
            2 -> return totalWater >= 350 && totalMilk >= 75 && totalBeans >= 20
            3 -> return totalWater >= 200 && totalMilk >= 100 && totalBeans >= 12
        }

        return false
    }
}
fun main() {
    var cm = CoffeeMachine()
    var loop = true

    do {
        loop = cm.action(readLine()!!)
    } while (loop)
}