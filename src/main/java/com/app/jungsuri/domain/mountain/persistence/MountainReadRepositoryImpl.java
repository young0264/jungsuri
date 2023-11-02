package com.app.jungsuri.domain.mountain.persistence;

import com.app.jungsuri.infra.pagination.MountainPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import java.util.List;

import static jooq.dsl.tables.Mountain.MOUNTAIN;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MountainReadRepositoryImpl implements MountainReadRepository {

    private final DSLContext dslContext;

    /** 모든 산 리스트 가져오기 */
    @Override
    public List<MountainEntity> findAllMountains() {
        return dslContext.select()
                .from(MOUNTAIN)
                .fetchInto(MountainEntity.class);
    }

    /** 페이지네이션 적용한 산 리스트 가져오기 */
    @Override
    public List<MountainEntity> findMountainListByPagination(int startRowNum) {
        return dslContext.select()
                .from(MOUNTAIN)
                .orderBy(MOUNTAIN.HEIGHT.desc())
                .limit(startRowNum-1, MountainPage.PAGE_ROW_SIZE.getValue())
                .fetchInto(MountainEntity.class);

//        log.info("MountainEntity startRowNum: {}", startRowNum);
//        /** cursor-페이지네이션 보류 */
//        return dslContext.select()
//                .from(MOUNTAIN)
//                .where(MOUNTAIN.ID.greaterThan((long) (startRowNum - 1)))
//                .orderBy(MOUNTAIN.HEIGHT.desc())
//                .limit(MountainPage.PAGE_ROW_SIZE.getValue())
//                .fetchInto(MountainEntity.class);
    }

    /** 모든 산 이름 가져오기*/
    @Override
    public List<String> findAllMountainsName() {
        return dslContext.select(MOUNTAIN.NAME)
                .from(MOUNTAIN)
                .fetchInto(String.class);
    }

    /** 산 이름으로 높이 가져오기 */
    @Override
    public int findMountainHeightByName(String name) {
        return dslContext.select(MOUNTAIN.HEIGHT)
                .from(MOUNTAIN)
                .where(MOUNTAIN.NAME.eq(name))
                .fetchOneInto(int.class);
    }

    /** mountain 정보 갯수 가져오기 */
    @Override
    public int getMountainCount() {
        return dslContext.selectCount()
                .from(MOUNTAIN)
                .fetchOneInto(Integer.class);
    }
}
