package contracts.data.v1

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    label 'postData'

    input {
        triggeredBy 'doPostData()'
    }

    outputMessage {
        sentTo 'data'
        body '''
        {
            "data" : "foo"
        }
        '''
    }
}

