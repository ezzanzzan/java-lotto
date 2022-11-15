package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.constants.Number;
import lotto.model.Lotto;
public class LottoMachineController {
    public static List<Lotto> operateLottoMachine(int purchaseNumber){
        Output.printPurchaseMsg(purchaseNumber);
        List<Lotto> lottos = LottoMachineController.initLottos(purchaseNumber);
        Output.printLottos(lottos);
        return lottos;
    }

    public static Lotto initLotto(){
        int rangeStart = Number.RANGE_START.getNumber();
        int rangeEnd = Number.RANGE_END.getNumber();
        int length = Number.LENGTH.getNumber();

        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(rangeStart, rangeEnd, length);
        List<Integer> lotto = new ArrayList<>(numbers);

        Collections.sort(lotto);
        Lotto newLotto = new Lotto(lotto);

        return newLotto;
    }

    public static List<Lotto> initLottos(int purchaseNumber){
        List<Lotto> lottos = new ArrayList<>();
        for(int i=0;i<purchaseNumber;i++){
            lottos.add(initLotto());
        }
        return lottos;
    }

    public static int comparePrizeNumber(List<Integer> lotto, List<Integer> win){
        int prizeNumber = 0;
        List<Integer> result = lotto.stream()
                .filter(old -> win.stream()
                        .anyMatch(Predicate.isEqual(old)))
                .collect(Collectors.toList());
        prizeNumber = result.size();
        return prizeNumber;
    }

    public static boolean isBonusNumber(List<Integer> lotto, int bonusNumber){
        if(lotto.contains(bonusNumber)){
            return true;
        }
        return false;
    }
    public static int getRankIndex(int prizeNumber, boolean isBonus){
        if(prizeNumber == Rank.FIRST.getWin()){
            return Rank.FIRST.getIndex();
        }
        if(prizeNumber == Rank.SECOND.getWin()&&isBonus){
            return Rank.SECOND.getIndex();
        }
        if(prizeNumber == Rank.THIRD.getWin()&&!isBonus){
            return Rank.THIRD.getIndex();
        }
        if(prizeNumber == Rank.FOURTH.getWin()){
            return Rank.FOURTH.getIndex();
        }
        if(prizeNumber == Rank.FIFTH.getWin()){
            return Rank.FIFTH.getIndex();
        }
        return Rank.REST.getIndex();
    }

    public static int getRankPrice(int index){
        if(index == Rank.FIRST.getIndex()){
            return Rank.FIRST.getPrice();
        }
        if(index == Rank.SECOND.getIndex()){
            return Rank.SECOND.getPrice();
        }
        if(index == Rank.THIRD.getIndex()){
            return Rank.THIRD.getPrice();
        }
        if(index == Rank.FOURTH.getIndex()){
            return Rank.FOURTH.getPrice();
        }
        if(index == Rank.FIFTH.getIndex()){
            return Rank.FIFTH.getPrice();
        }
        return Rank.REST.getPrice();
    }

    public static int[] toListLotto(Lotto lotto){
        int[] lottoArr = new int[lotto.getSize()];
        for(int i=0;i< lotto.getSize();i++){
            lottoArr[i] = lotto.getNumber(i);
        }
        return lottoArr;
    }
}