package com.devkproject.movieinfo2

/**
buffer : 버퍼는 지정된 용량의 채널을 통하여 값을 방출하며 별도의 코루틴에서 collector를 실행합니다.
cancellable : 각 방출에 대한 취소 상태를 확인하고 flow 콜렉터가 취소된 경우 해당 취소 원인을 던지는 flow를 반환합니다. flow 빌더 및 SharedFlow의 모든 구현은 기본적으로 cancellable 될 수 있습니다.
catch : flow 완료되었을 때 예외를 포착하고 포착한 예외와 함께 인자에 지정된 action을 호출합니다. 이 연산자는 다운스트림 흐름에서 발생하는 예외에 대해 투명하며 flow를 취소하기 위해 던지는 예외는 catch하지 않습니다.
collect : 1. 인자가 지정되지 않은 익스텐션은 지정된 flow를 수집하지만 모든 방출된 값을 무시하는 터미널 flow 연산자 입니다. 수집 중 또는 제공된 flow에서 예외가 발생하는 경우 이 예외는 이 메소드에서 다시 발생합니다. 2. 인자가 지정된 익스텐션은 제공된action으로 지정된 flow를 수집하는 터미널 flow 연산자입니다.
collectIndexed : 엘리먼트(0 기반)와 인덱스를 취하는 제공된 action으로 지정된 flow를 수집하는 터미널 flow 연산자입니다.
collectLatest : 제공된 action을 사용하여 지정된 flow를 수집하는 터미널 flow 연산자입니다. collect와 중요한 차이점은 원래 flow가 새 값을 방출하면 이전 값에 대한 action 블록이 취소된다는 것입니다.
combine : 각 flow에서 가장 최근에 방출한 값을 조합하여 인자로 주어진 transform 함수와 함께 값이 생성된 flow를 반환합니다.
combineTransform : 각 flow에서 가장 최근에 방출한 값을 처리하는 transform 함수에 의해 값이 생성되는 flow를 반환합니다.
conflate : 압축된 채널을 통해 flow 방출값을 압축하여 별도의 코루틴에서 콜렉터를 실행합니다. 이로 인해 콜렉터가 느려서 방출자가 일시 중단되지 않지만 콜렉터는 항상 가장 최근의 값을 방출한다는 것입니다.
count : flow의 요소 수를 반환합니다. predicate 인자를 넣어주면 predicate 조건에 해당하는 요소의 수만 반환합니다.
debounce : 원래 flow와 동일한 flow를 반환하지만, 지정된 제한 시간(timeout) 안에 새로운 값 뒤에 오는 값은 필터링합니다. 항상 최신 값이 방출됩니다.
distinctUntilChanged : 1. 인자가 없는 경우, 동일한 값의 후속 반복이 모두 필터링되는 flow를 반환합니다. 2. 제공된 areEquivalent 인자를 통해 동일한 값의 후속 반복을 비교하며 마찬가지로 필터링하는 flow를 반환합니다.
distinctUntilChangedBy : 동일한 키의 후속 반복이 모두 필터링되고 인자로 주어진 keySelector 함수로 키를 추출하는 flow를 반환합니다.
drop : 인자로 주어진 count가 음수일 경우 예외를 던지며, 값을 방출하기 시작할 때 주어진 count 값 만큼 방출값을 무시하는 flow를 반환합니다.
filter : 지정된 predicate와 일치하는 값만 포함하고 있는 flow를 반환합니다.
filterInstance : 지정된 타입 R의 인스턴스 값만 포함하는 flow를 반환합니다.
filterNot : 지정된 predicate와 일치하지 않는 값만 포함하고 있는 flow를 반환합니다.
filterNotNull : null이 아닌 값만 포함하는 flow를 반환합니다.
first : 1. 인자가 없는 경우 첫 번째 요소를 반환한 후 flow의 수집을 취소하는 터미널 연산자입니다. flow가 비어있는 경우 NoSuchElementException을 던집니다. 2. 지정된 predicate와 일치하는 flow에서 방출된 첫 번째 요소를 반환한 후 flow의 수집을 취소하는 터미널 연산자입니다. 마찬가지로 flow가 비어있는 경우 동일한 에러를 던집니다.
firstOrNull : first와 비슷하지만 flow가 비어있으면 null을 반환합니다.
flatMapConcat : 인자로 주어진 transform을 적용하고 flow를 반환한 다음 이러한 flow들을 연결 및 평탄화하여 원래 flow에서 방출되는 요소를 변환합니다,
flatMapLatest : 원래 flow가 값을 방출할 때마다 transform 함수에 의해 생성된 새로운 flow로 전환된 flow를 반환합니다. 원래 flow가 새 값을 방출하면 transform 블록에서 생성된 이전 flow는 취소됩니다.
flatMapMerge : transform을 적용하고 다른 flow를 반환한 후 이러한 flow를 병합 및 평탄화하여 원래 flow에서 방출되는 요소를 반환합니다.
flattenConcat : 인자로 주어진 flow를 끼우지 않고 순차적인 단일 flow로 지정된 flow로 평탄화합니다. 이 메소드는 개념적으로 flattenMerge(concurrency = 1)과 동일하지만 구현 속도가 더 빠릅니다.
flattenMerge : 동시에 수집되는 flow 수에 대한 concurrency 제한이 있는 단일 flow로 지정된 flow를 평탄화합니다.
flowOn : 이 flow가 실행되는 context를 지정된 context로 변경합니다. 이 연산자는 합성 가능하며 고유한 context가 없는 이전 연산자에만 영향을 미칩니다. 이 연산자는 context 보존 특성을 가집니다. 또한 context는 다운스트림 flow로 유출되지 않습니다.
flowWith : builder 내에서 지정된 flow에 적용된 모든 변환이 실행되는 context를 변경하는 연산자입니다. 이 연산자는 context 보존 특성을 가지며 이전 및 후속 작업의 context에는 영향을 주지 않습니다.
fold : initial 값부터 시작하여 operation 인자의 acc 값과 현재 값으로 계산된 값과 각 요소를 적용하여 값을 누적합니다.
launchIn : scope에서 지정된 flow의 컬렉션을 시작하는 터미널 flow 연산자입니다. scope.launch { flow.collect() } 의 줄임입니다.
map : 지정된 transform 함수를 원래 flow의 각 값에 적용한 결과가 포함된 flow를 반환합니다.
mapLatest : transform 함수에 의해 변환된 원래 flow에서 요소를 방출하는 flow를 반환합니다. 원래 flow가 새 값을 방출하면 이전 값의 transform 블록 작업이 취소됩니다.
mapNotNull : 지정된 transform 함수를 원래 flow의 각 값에 적용한 null이 아닌 결과만 포함하는 flow를 반환합니다.
onCompletion : 취소 예외 또는 실패를 action 매개 변수로 전달하여 flow가 완료되거나 취소된 후 지정된 action을 호출하는 flow를 반환합니다.
onEach : 업스트림 flow의 각 값이 다운스트림에서 방출되기 전에 지정된 action을 호출하는 flow를 반환합니다.
onEmpty : 이 flow가 완료되었을 때 요소를 방출하지 않고 지정된 action을 호출합니다. action의 수신자는 FlowCollector 이므로 onEmpty는 추가 요소를 방출할 수 있습니다. 예를 들어 다음과 같습니다.
onStart : 이 flow가 수집되기 전에 지정된 action을 호출하는 flow를 반환합니다.
produceIn : 지정된 flow를 수집하는 생상적인 코루틴을 만듭니다.
reduce : 첫 번째 요소부터 시작하여 현재 계산 값 및 각 요소에 operation을 적용합니다. flow가 비어있는 경우 NoSuchElementException을 던집니다.
retry : 지정된 predicate와 일치하는 예외가 업스트림 flow에서 발생할 경우 최대 retries 횟수까지 지정된 flow의 수집을 재시도 합니다. 이 연산자는 다운스트림 flow에서 발생하는 예외에 대해 투명하며 flow를 취소하기 위해 발생하는 예외에 대해서는 재시도하지 않습니다.
retryWhen : 업스트림 flow에서 예외가 발생하고 predicate 가 true를 반환할 때 지정된 flow의 수집을 다시 시도합니다. predicate는 또한 초기 호출 시 0부터 시작하는 attempt 숫자를 매개 변수로 수신합니다. 이 연산자는 다운스트림 flow에서 발생하는 예외에 대해 투명하며 flow를 취소하기 위해 발생하는 예외에 대해서는 재시도하지 않습니다.
sample : 지정된 샘플링 period 동안 원래 flow에서 방출된 최신 값만 방출되는 flow를 반환합니다.
scan : operation 지정된 flow을 접고 initial 값을 포함한 모든 중간 결과를 내보냅니다. 초기 값은 서로 다른 콜렉터 간에 공유되므로 변경할 수 없거나 변경해서는 안 됩니다. 예를 들어 다음과 같습니다.
sharedIn : 콜드 flow를 지정된 코루틴 scope에서 시작되는 핫 SharedFlow로 변환하여 업스트림 flow의 단일 실행 인스턴스에서 방출된 방출값을 다운스트림 구독자와 공유하고 replay 수 만큼의 값을 새 구독자에게 발행합니다.
single : 하나의 값만 내보내기를 기다리는 터미널 연산자입니다. 둘 이상의 요소가 포함된 flow의 경우 IllgalStateException을 던지고 빈 요소로 구성된 flow의 경우 NoSuchElementException을 던집니다.
singleOrNull : single과 유사하지만 에러가 발생하는 상황에서 에러 대신 null을 반환합니다.
stateIn : 1. scope, started, initialValue 인자를 모두 지정한 경우, 콜드 Flow를 지정된 코루틴 scope에서 시작되는 핫 StateFlow로 변환하여 업스트림 flow의 단일 실행 인스턴스에서 가장 최근에 내보낸 값을 다운스트림 구독자 여러 명과 공유합니다. 2. scope 인자만 지정한 경우, 지정된 scope에서 업스트림 flow를 시작하고, 첫 번째 값이 방출될 때까지 일시 중단하며, 향후 방출되는 핫 StateFlow를 반환하여 업스트림 flow의 실행 인스턴스에서 가장 최근에 방출된 값을 여러 다운스트림 구독자와 공유합니다.
take : 첫 번째 부터 count 수 만큼의 요소를 포함하는 flow를 반환합니다. count 수 만큼 소비되면 원래 flow는 취소됩니다.
takeWhile : 지정된 predicate를 만족하는 첫 번째 요소를 포함하는 flow를 반환합니다.
transform : 지정된 flow의 각 값에 인자로 주어진 transform 기능을 적용합니다.
transformLatest : 원래 flow가 값을 방출할 때마다 transform으로 요소를 생성하는 flow를 반환합니다. 원래 flow가 새 값을 방출하면 이전에 있던 transform 블록은 취소됩니다.
transformWhile : 이 함수가 true를 반환하는 동안 지정된 flow의 각 값에 transform 함수를 적용합니다.
withIndex : 값과 해당 인덱스(0부터 시작)를 포함하는 각 요소를 IndexedValue로 감싸는 flow를 반환합니다.
zip : 각 값 쌍에 적용된 제공된 transform 함수를 사용하여 other(인자로 주어진) flow와 함께 현재 flow(this)의 값을 압축합니다. 결과 flow 중 하나가 완료되는 즉시 flow가 완료되고 나머지 flow는 취소됩니다.
references : https://medium.com/hongbeomi-dev/kotlin-coroutine-flow-ac07cfdca42d
 */
